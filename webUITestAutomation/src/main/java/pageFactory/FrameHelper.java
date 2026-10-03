package pageFactory;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import annotations.IFrame;
import annotations.IFrames;
import testBase.BaseClass;

/**
*
* Helper class for switching to frames in Selenium.
*/
public class FrameHelper {
	FrameHelper() {
	}

	/**
	 * Switches to the frame specified by the given field.
	 *
	 * @param field the field representing the frame
	 */
	public static void switchToFrame(final Field field) {
		if (field != null) {
			BaseClass.getDriver().switchTo().defaultContent();
			if (field.getAnnotation(IFrames.class) != null) {
				final IFrames iframes = field.getAnnotation(IFrames.class);
				final IFrame[] frames = iframes.value();
				if (frames.length > 0) {
					for (final IFrame eachFrame : frames) {
						if (!ignoreIframe(eachFrame)) {
							if (StringUtils.isNotBlank(eachFrame.xpath())) {
								final WebElement frameElement = BaseClass.getDriver()
										.findElement(By.xpath(eachFrame.xpath()));
								BaseClass.getDriver().switchTo().frame(frameElement);
							} else if (StringUtils.isNotBlank(eachFrame.name())) {
								BaseClass.getDriver().switchTo().frame(eachFrame.name());
							}
						}
					}
				}
			}
		}

	}

	/**
	 * Checks if the given frame should be ignored based on the frame_to_ignore parameter.
	 *
	 * @param frame the frame to check
	 * @return true if the frame should be ignored, false otherwise
	 */
	private static boolean ignoreIframe(final IFrame frame) {
		final String frameToIgnore = BaseClass.getParameter("frame_to_ignore", "");
		if (!StringUtils.isEmpty(frameToIgnore)) {
			final String[] attrToIgnore = frameToIgnore.split(":");
			if (attrToIgnore.length != 2) {
				return false;
			}
			if (frame.name() != null && attrToIgnore[0].equals("name") && frame.name().contains(attrToIgnore[1])) {
				return true;
			}
			if (frame.xpath() != null && attrToIgnore[0].equals("xpath") && frame.xpath().contains(attrToIgnore[1])) {
				return true;
			}
			final String[] attributes = frame.attributes();
			for (final String each : attributes) {
				try {
					final String[] attPair = each.split("=");
					if (attrToIgnore[0].equals(attPair[0].trim()) && attPair[1].trim().contains(attrToIgnore[1])) {
						return true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}
