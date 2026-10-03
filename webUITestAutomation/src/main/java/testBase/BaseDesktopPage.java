package testBase;

import java.io.File;

import org.sikuli.script.ImagePath;
import org.sikuli.script.Screen;

import pageFactory.desktop.FindByImageResourceLocation;
import pageFactory.desktop.SikuliFactory;

/**
* The BaseDesktopPage class is a generic class that serves as the base class for desktop page objects in the test framework.
* It provides a Screen instance from the Sikuli library and initializes the SikuliFactory for the page object.
* The class is parameterized with the type B, which represents the specific subclass of BaseDesktopPage.
*/
public class BaseDesktopPage<B extends BaseDesktopPage<B>>{

	private Screen sikuli;
	
	/**
	 * Constructs a new instance of BaseDesktopPage and initializes the Sikuli Screen and SikuliFactory.
	 * It retrieves the image path from the FindByImageResourceLocation annotation on the class.
	 * The image path is then added to the Sikuli ImagePath for locating images.
	 */
	public BaseDesktopPage() {
		this.sikuli = ThreadUtils.getScreenRef();
		String imgPath = this.getClass().getDeclaredAnnotation(FindByImageResourceLocation.class).value();
		String path = (System.getProperty("user.dir") + imgPath).replace("\\", File.separator);
		if (path.endsWith(File.separator)){
			path = path.substring(0,path.length()-1);
		}
		System.out.println("imgpath"+ path.replace("\\", File.separator));
//		ImagePath.reset();
		System.out.println(ImagePath.add(path.replace("\\", File.separator)));
		SikuliFactory.initElements(sikuli, this);
	}
}