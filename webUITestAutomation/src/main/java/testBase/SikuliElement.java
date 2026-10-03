package testBase;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class SikuliElement {

	// Fields
	private Screen sikuli;
	private String image;
	private String[] images;
	private float similarity0to100;
	private int x;
	private int y;
	private String imagePath;
	
	// Constructor
	public SikuliElement(Screen sikuli, String imagePath, String image, String[] images, float similarity0to100, int x, int y) {
		super();
		this.sikuli = sikuli;
		this.imagePath = imagePath;
		this.image = image;
		this.images = images;
		this.similarity0to100 = similarity0to100;
		this.x = x;
		this.y = y;
	}

	// Getters
	public String getImage() {
		return image;
	}
	
	public String getImagePath() {
		return imagePath;
	}

	public String[] getImages() {
		return images;
	}

	public float getSimilarity0to100() {
		return similarity0to100;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setImage(String image) {
		this.image = image;
	}

	/** ----------- Desktop Actions ------------ */
	/**
	 * Desktop Actions
	 */
	
	/**
	 * Clicks on the SikuliElement.
	 * @return the number of successful clicks (0 or 1)
	 */
	public int click() {
		try {
			return sikuli.click(createPattern(this));
		} catch (FindFailed e) {
			try {
				return sikuli.click(createNewPatternFromPath(this));
			} catch (FindFailed e1) {
				BaseClass.screenshot("Failed finding " + this.image);
				throw new RuntimeException(e1);
			}
		}
	}

	/**
	 * Performs a click action on the SikuliElement with specified modifiers.
	 *
	 * @param modifiers the keyboard modifiers to be used during the click
	 * @return the number of occurrences of the image found and clicked
	 */
	public int click(Integer modifiers) {
		try {
			return sikuli.click(createPattern(this), modifiers);
		} catch (FindFailed e) {
			try {
				return sikuli.click(createNewPatternFromPath(this), modifiers);
			} catch (FindFailed e1) {
				BaseClass.screenshot("Failed finding " + this.image);
				throw new RuntimeException(e1);
			}
		}
	}

	/**
	 * Performs a click action on the SikuliElement with another SikuliElement as a reference point.
	 *
	 * @param sikuliElement the reference SikuliElement
	 * @return the number of occurrences of the image found and clicked
	 */
	public int click(SikuliElement sikuliElement) {
		try {
			return sikuli.find(createPattern(this)).click(createPattern(sikuliElement));
		} catch (FindFailed e) {
			try {
				return sikuli.find(createNewPatternFromPath(this)).click(createNewPatternFromPath(this));
			} catch (FindFailed e1) {
				BaseClass.screenshot("Failed finding " + this.image);
				throw new RuntimeException(e1);
			}
		}
	}

	/**
	 * Performs a double-click action on the SikuliElement.
	 * 
	 * @return the number of successful double-clicks (0 or 1)
	 */
	public int doubleClick() {
		try {
			return sikuli.doubleClick(createPattern(this));
		} catch (FindFailed e) {
			try {
				return sikuli.doubleClick(createNewPatternFromPath(this));
			} catch (FindFailed e1) {
				BaseClass.screenshot("Failed finding " + this.image);
				throw new RuntimeException(e1);
			}
		}
	}

	/**
	 * Performs a double-click action on the SikuliElement with specified modifiers.
	 *
	 * @param modifiers the keyboard modifiers to be used during the double-click
	 * @return the number of occurrences of the image found and double-clicked
	 */
	public int doubleClick(Integer modifiers) {
		try {
			return sikuli.doubleClick(createPattern(this), modifiers);
		} catch (FindFailed e) {
			try {
				return sikuli.doubleClick(createNewPatternFromPath(this), modifiers);
			} catch (FindFailed e1) {
				BaseClass.screenshot("Failed finding " + this.image);
				throw new RuntimeException(e1);
			}
		}
	}

	/**
	 * Performs a right-click action on the SikuliElement.
	 * 
	 * @return the number of successful right-clicks (0 or 1)
	 */
	public int rightClick() {
		try {
			return sikuli.rightClick(createPattern(this));
		} catch (FindFailed e) {
			try {
				return sikuli.rightClick(createNewPatternFromPath(this));
			} catch (FindFailed e1) {
				BaseClass.screenshot("Failed finding " + this.image);
				throw new RuntimeException(e1);
			}
		}
	}
	
	/**
	 * Performs a drag-and-drop action from the current SikuliElement to the specified SikuliElement.
	 *
	 * @param sikuliElement the target SikuliElement
	 * @return the number of occurrences of the source image found and dragged
	 */
	public int dragDrop(SikuliElement sikuliElement) {
		try {
			return sikuli.dragDrop(createPattern(this), createPattern(sikuliElement));
		} catch (FindFailed e) {
			try {
				return sikuli.dragDrop(createNewPatternFromPath(this),createPattern(sikuliElement));
			} catch (FindFailed e1) {
				BaseClass.screenshot("Failed finding " + this.image);
				throw new RuntimeException(e1);
			}
		}
	}

	/**
	 * Performs a drag action on the SikuliElement.
	 * 
	 * @return the number of successful drag actions (0 or 1)
	 */
	public int drag() {
		try {
			return sikuli.drag(createPattern(this));
		} catch (FindFailed e) {
			try {
				return sikuli.drag(createNewPatternFromPath(this));
			} catch (FindFailed e1) {
				BaseClass.screenshot("Failed finding " + this.image);
				throw new RuntimeException(e1);
			}
		}
	}

	/**
	 * Performs a drop action at the SikuliElement.
	 * 
	 * @return the number of successful drop actions (0 or 1)
	 */
	public int dropAt() {
		try {
			return sikuli.dropAt(createPattern(this));
		} catch (FindFailed e) {
			try {
				return sikuli.dropAt(createNewPatternFromPath(this));
			} catch (FindFailed e1) {
				BaseClass.screenshot("Failed finding " + this.image);
				throw new RuntimeException(e1);
			}
		}
	}

	/**
	 * Moves the mouse cursor to the SikuliElement.
	 * 
	 * @return the number of successful mouse moves (0 or 1)
	 */
	public int mouseMove() {
		try {
			return sikuli.mouseMove(createPattern(this));
		} catch (FindFailed e) {
			try {
				return sikuli.mouseMove(createNewPatternFromPath(this));
			} catch (FindFailed e1) {
				BaseClass.screenshot("Failed finding " + this.image);
				throw new RuntimeException(e1);
			}
		}
	}

//	public int wheel(int direction, int steps) {
//		try {
//			return sikuli.wheel(createPattern(this), direction, steps);
//		} catch (FindFailed e) {
//			throw new RuntimeException(e);
//		}
//	}
//	
//	public int wheel(int direction, int steps, int stepDelay) {
//		try {
//			return sikuli.wheel(createPattern(this), direction, steps , stepDelay);
//		} catch (FindFailed e) {
//			throw new RuntimeException(e);
//		}
//	}

	/**
	 * Moves the mouse cursor to the SikuliElement and hovers over it.
	 *
	 * @return the number of successful hover actions (0 or 1)
	 */
	public int hover() {
		try {
			return sikuli.hover(createPattern(this));
		} catch (FindFailed e) {
			try {
				return sikuli.hover(createNewPatternFromPath(this));
			} catch (FindFailed e1) {
				BaseClass.screenshot("Failed finding " + this.image);
				throw new RuntimeException(e1);
			}
		}
	}

	/**
	 * Checks if the SikuliElement exists within the default auto-wait timeout.
	 *
	 * @return true if the SikuliElement exists, false otherwise
	 */
	public boolean exists() {
		return exists((int) sikuli.getAutoWaitTimeout());
	}

	/**
	 * Checks if the SikuliElement exists within the specified timeout.
	 *
	 * @param timeoutInSeconds the timeout value in seconds
	 * @return true if the SikuliElement exists, false otherwise
	 */
	public boolean exists(int timeoutInSeconds) {
		Pattern pattern;
		try {
			pattern = createPattern(this, timeoutInSeconds);
		} catch (Exception e) {
			return false;
		}
		Match imageMatch = sikuli.exists(pattern, timeoutInSeconds);
		boolean imageExists = imageMatch != null;
		return imageExists;
	}

	/**
	 * Waits for the SikuliElement to appear within the default auto-wait timeout.
	 *
	 * @return the Match object representing the found SikuliElement
	 */
	public Match waitit() {
		return wait((int) sikuli.getAutoWaitTimeout());
	}

	/**
	 * Waits for the SikuliElement to appear within the specified timeout.
	 *
	 * @param timeoutInSeconds the timeout value in seconds
	 * @return the Match object representing the found SikuliElement
	 */
	public Match wait(int timeoutInSeconds) {
		try {
			return sikuli.wait(createPattern(this, timeoutInSeconds), timeoutInSeconds);
		} catch (FindFailed e) {
			try {
				return sikuli.wait(createNewPatternFromPath(this, timeoutInSeconds), timeoutInSeconds);
			} catch (FindFailed e1) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e1);
			}
		}
	}

//	public boolean waitVanish() {
//		return waitVanish((int) sikuli.getAutoWaitTimeout());
//	}
//
//	public boolean waitVanish(int timeoutInSeconds) {
//		Pattern pattern;
//		try {
//			pattern = createPattern(this, 0);
//		} catch (Exception e) {
//			return true;
//		}
//		boolean imageVanished = sikuli.waitVanish(pattern, timeoutInSeconds);
//		if (imageVanished) {
//			return true;
//		}
//		throw new RuntimeException(
//				new FindFailed("Image \"" + image + "\" not vanished after " + timeoutInSeconds + " seconds."));
//	}
//
//	public Match find() {
//		try {
//			return sikuli.find(createPattern(this));
//		} catch (FindFailed e) {
//			throw new RuntimeException(e);
//		}
//	}
//	
//	public Match findBest() {
//		return sikuli.findBest(createPattern(this));
//	}
//	
//	public Iterator<Match> findAll() {
//		try {
//			return sikuli.findAll(createPattern(this));
//		} catch (FindFailed e) {
//			throw new RuntimeException(e);
//		}
//	}
//	
//	public List<Match> findAllByRow() {
//		return sikuli.findAllByRow(createPattern(this));
//	}
//	
//	public String onAppear() {
//		return sikuli.onAppear(createPattern(this));
//	}
//	
//	public String onAppear(Object observer) {
//		return sikuli.onAppear(createPattern(this), observer);
//	}
//	
//	public String onVanish(Object observer) {
//		return sikuli.onVanish(createPattern(this), observer);
//	}
//	
//	public String onVanish() {
//		return sikuli.onVanish(createPattern(this));
//	}
//	
//	public List<Match> findAllByColumn() {
//		return sikuli.findAllByColumn(createPattern(this));
//	}

	/**
	 * Clears the text input field by performing a series of actions: click, select all, and backspace.
	 *
	 * @return the number of successful backspace actions
	 * @throws RuntimeException if an exception occurs during the clearing process
	 */
	private int clear() {
		try {
			this.click();
			this.type("a", Key.CTRL);
			return this.type(Key.BACKSPACE);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Types the specified text after clearing the text input field.
	 *
	 * @param text the text to be typed
	 * @return the number of successful type actions
	 * @throws RuntimeException if an exception occurs during the typing process
	 */
	public int type(String text) {
		try {
			clear();
			return sikuli.type(createPattern(this), text);
		} catch (FindFailed e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Types the specified text after clearing the text input field and presses the TAB key afterwards.
	 *
	 * @param text the text to be typed
	 * @return the number of successful type actions
	 * @throws RuntimeException if an exception occurs during the typing process
	 */
	public int typeWithTab(String text) {
		try {
			int result = 0;
			clear();
			result = sikuli.type(createPattern(this), text);
			sikuli.type(Key.TAB);
			return result;
		} catch (FindFailed e) {
			try {
				return sikuli.type(createNewPatternFromPath(this), text);
			} catch (FindFailed e1) {
				BaseClass.screenshot("Failed finding " + this.image);
				throw new RuntimeException(e1);
			}
		}
	}
	
	/**
	 * Types the specified text with the given modifiers.
	 *
	 * @param text      the text to be typed
	 * @param modifiers the modifiers to be applied during typing
	 * @return the number of successful type actions
	 * @throws RuntimeException if an exception occurs during the typing process
	 */
	public int type(String text, String modifiers) {
		try {
			return sikuli.type(createPattern(this), text, modifiers);
		} catch (FindFailed e) {
			try {
				return sikuli.type(createNewPatternFromPath(this), text, modifiers);
			} catch (FindFailed e1) {
				BaseClass.screenshot("Failed finding " + this.image);
				throw new RuntimeException(e1);
			}
			
		}
	}
	
	/**
	 * Types the specified text with the given modifiers.
	 *
	 * @param text      the text to be typed
	 * @param modifiers the modifiers to be applied during typing
	 * @return the number of successful type actions
	 * @throws RuntimeException if an exception occurs during the typing process
	 */
	public int type(String text, int modifiers) {
		try {
			return sikuli.type(createPattern(this), text, modifiers);
		} catch (FindFailed e) {
			try {
				return sikuli.type(createNewPatternFromPath(this), text, modifiers);
			} catch (FindFailed e1) {
				BaseClass.screenshot("Failed finding " + this.image);
				throw new RuntimeException(e1);
			}
		}
	}

	/**
	 * Pastes the specified text at the current cursor position.
	 *
	 * @param text the text to be pasted
	 * @return the number of successful paste actions
	 * @throws RuntimeException if an exception occurs during the pasting process
	 */
	public int paste(String text) {
		try {
			return sikuli.paste(createPattern(this), text);
		} catch (FindFailed e) {
			try {
				return sikuli.paste(createNewPatternFromPath(this), text);
			} catch (FindFailed e1) {
				BaseClass.screenshot("Failed finding " + this.image);
				throw new RuntimeException(e1);
			}
		}
	}

	/**
	 * Retrieves the text content from the text input field.
	 *
	 * @return the text content of the text input field
	 * @throws RuntimeException if an exception occurs during the retrieval process
	 */
	public String getText() {
		clearClipboard();
		sikuli.type("a", Key.CTRL);
		sikuli.type("c", Key.CTRL);
		return getClipboard();
	}
	
	/**
	 * Clears the system clipboard by setting its content to an empty string.
	 */
	private void clearClipboard() {
		StringSelection selection = new StringSelection("");
		Clipboard clipboard2 = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard2.setContents(selection, selection);
	}

	/**
	 * Retrieves the content from the system clipboard as a string.
	 *
	 * @return the content of the system clipboard
	 * @throws RuntimeException if an exception occurs during the retrieval process
	 */
	private String getClipboard() {
		String clipboardText = "";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		try {
			clipboardText = (String) clipboard.getData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return clipboardText;
	}

	/**
	 * Sets the image of the SikuliElement if multiple images are available and waits for it to appear.
	 * Uses the default auto-wait timeout.
	 *
	 * @param sikuliElement the SikuliElement object to set the image for
	 */
	private void setImageIfUrlsIsSet(SikuliElement sikuliElement) {
		setImageIfUrlsIsSet(sikuliElement, (int) sikuli.getAutoWaitTimeout());
	}

	/**
	 * Sets the image of the SikuliElement if multiple images are available and waits for it to appear
	 * within the specified timeout.
	 *
	 * @param sikuliElement     the SikuliElement object to set the image for
	 * @param timeoutInSeconds the timeout value in seconds
	 */
	private void setImageIfUrlsIsSet(SikuliElement sikuliElement, int timeoutInSeconds) {
		if (sikuliElement.getImages().length > 1) {
			boolean imageFound = false;
			long timeoutExpiredMs = System.currentTimeMillis() + timeoutInSeconds * 1000;
			while (!imageFound) {
				for (int i = 0; i < sikuliElement.getImages().length; i++) {
					imageFound = sikuli.exists(new Pattern(sikuliElement.getImages()[i])
							.similar(sikuliElement.getSimilarity0to100() / 100), 0) != null;
					if (imageFound) {
						sikuliElement.setImage(sikuliElement.getImages()[i]);
						return;
					}
				}
				long waitMs = timeoutExpiredMs - System.currentTimeMillis();
				if (waitMs <= 0) {
					break;
				}
			}
			if (!imageFound) {
				throw new RuntimeException(
						new FindFailed("Images not found: " + Arrays.toString(sikuliElement.getImages())));
			}
		}
	}

	/**
	 * Creates a Sikuli Pattern object for the specified SikuliElement.
	 * Waits for the image to appear using the default auto-wait timeout.
	 *
	 * @param sikuliElement the SikuliElement object to create the pattern for
	 * @return the created Pattern object
	 */
	private Pattern createPattern(SikuliElement sikuliElement) {
		ThreadUtils.setIsScreen(true);
		setImageIfUrlsIsSet(sikuliElement);
		Pattern p = new Pattern(sikuliElement.getImage()).similar(sikuliElement.getSimilarity0to100() / 100)
														.targetOffset(sikuliElement.getX(), sikuliElement.getY());
		return p;

	}

	/**
	 * Creates a Sikuli Pattern object for the specified SikuliElement.
	 * Waits for the image to appear within the specified timeout.
	 *
	 * @param sikuliElement     the SikuliElement object to create the pattern for
	 * @param timeoutInSeconds the timeout value in seconds
	 * @return the created Pattern object
	 */
	private Pattern createPattern(SikuliElement sikuliElement, int timeoutInSeconds) {
		setImageIfUrlsIsSet(sikuliElement, timeoutInSeconds);
		return new Pattern(sikuliElement.getImage()).similar(sikuliElement.getSimilarity0to100() / 100)
					.targetOffset(sikuliElement.getX(), sikuliElement.getY());
	}
	
	/**
	 * Creates a new Sikuli Pattern object for the specified SikuliElement using the image path.
	 * Waits for the image to appear using the default auto-wait timeout.
	 *
	 * @param sikuliElement the SikuliElement object to create the pattern for
	 * @return the created Pattern object
	 */
	private Pattern createNewPatternFromPath(SikuliElement sikuliElement) {
		String YYYYY1 = sikuliElement.getImagePath() + sikuliElement.getImage();
		System.out.println("YYYYY1"+ YYYYY1 );
		
		try {
			String jarPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
			System.out.println("jarPath : "+ jarPath );
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		 
		String Ipath = new File(System.getProperty("user.dir") + sikuliElement.getImagePath() + sikuliElement.getImage()).getPath();
		return new Pattern(Ipath).similar(sikuliElement.getSimilarity0to100() / 100).targetOffset(sikuliElement.getX(), sikuliElement.getY());
	}

	/**
	 * Creates a new Sikuli Pattern object for the specified SikuliElement using the image path.
	 * Waits for the image to appear within the specified timeout.
	 *
	 * @param sikuliElement     the SikuliElement object to create the pattern for
	 * @param timeoutInSeconds the timeout value in seconds
	 * @return the created Pattern object
	 */
	private Pattern createNewPatternFromPath(SikuliElement sikuliElement, int timeoutInSeconds) {
		setImageIfUrlsIsSet(sikuliElement, timeoutInSeconds);
		String Ipath = new File(System.getProperty("user.dir") +sikuliElement.getImagePath() + sikuliElement.getImage()).getPath();
		return new Pattern(Ipath).similar(sikuliElement.getSimilarity0to100() / 100).targetOffset(sikuliElement.getX(), sikuliElement.getY());
	}


}
