package pageFactory;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

/**
*
* A factory for creating element locators based on the given search context.
*/
public final class DefaultElementLocatorFactory implements ElementLocatorFactory {
	private final SearchContext searchContext;

	/**
	 * Constructs a DefaultElementLocatorFactory with the specified search context.
	 *
	 * @param searchContext the search context to use for locating elements
	 */
	public DefaultElementLocatorFactory(SearchContext searchContext) {
		this.searchContext = searchContext;
	}

	/**
	 * Creates an element locator for the given field.
	 *
	 * @param field the field for which to create the element locator
	 * @return the created element locator
	 */
	public ElementLocator createLocator(Field field) {
		return new DefaultElementLocator(searchContext, field);
	}
}
