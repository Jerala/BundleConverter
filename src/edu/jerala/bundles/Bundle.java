package edu.jerala.bundles;

import java.util.ListResourceBundle;

/**
 * Resource bundle with general strings (concerning files).
 * This implementation is used if a bundle for the current user locale is not found.
 */
public class Bundle extends ListResourceBundle {
    /**
     * @see java.util.ListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][]{
                {"Error", "Error"},
                {"File", "Русский_текст"},
                {"inFile", "in file"},
                {"notFound", "not found"},
                {"CantDelete", "Can't delete"},
                {"CantCreate", "Can't create"}//not used yet
        };
    }
}