package pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;
import java.util.List;

/**
 * The default element locator, which will lazily locate an element or an
 * element list on a page. This class is designed for use with the
 * {@link org.openqa.selenium.support.PageFactory} and understands the
 * annotations {@link org.openqa.selenium.support.FindBy} and
 * {@link org.openqa.selenium.support.CacheLookup}.
 */
public class DefaultElementLocator implements ElementLocator {
	private final SearchContext searchContext;
	private final boolean shouldCache;
	private final By by;
	private WebElement cachedElement;
	private List<WebElement> cachedElementList;
	private Field field;

	/**
	 * Creates a new element locator.
	 *
	 * @param searchContext The context to use when finding the element
	 * @param field         The field on the Page Object that will hold the located
	 *                      value
	 */
	public DefaultElementLocator(SearchContext searchContext, Field field) {
		this(searchContext, new Annotations(field));
		this.field = field;
	}

	/**
	 * Use this constructor in order to process custom annotaions.
	 *
	 * @param searchContext The context to use when finding the element
	 * @param annotations   AbstractAnnotations class implementation
	 */
	public DefaultElementLocator(SearchContext searchContext, AbstractAnnotations annotations) {
		this.searchContext = searchContext;
		this.shouldCache = annotations.isLookupCached();
		this.by = annotations.buildBy();
	}

	/**
	 * Find the element.
	 */
	public WebElement findElement() {
		FrameHelper.switchToFrame(field);
		if (cachedElement != null && shouldCache()) {
			return cachedElement;
		}

		WebElement element = searchContext.findElement(by);
		if (shouldCache()) {
			cachedElement = element;
		}

		return element;
	}

	/**
	 * Find the element list.
	 */
	public List<WebElement> findElements() {
		FrameHelper.switchToFrame(field);
		if (cachedElementList != null && shouldCache()) {
			return cachedElementList;
		}

		List<WebElement> elements = searchContext.findElements(by);
		if (shouldCache()) {
			cachedElementList = elements;
		}

		return elements;
	}

	/**
	 * Returns whether the element should be cached.
	 *
	 * @return {@code true} if the element should be cached
	 */
	protected boolean shouldCache() {
		return shouldCache;
	}

	@Override
	public String toString() {
		FrameHelper.switchToFrame(field);
		return this.getClass().getSimpleName() + " -> " + this.by;
	}
}
