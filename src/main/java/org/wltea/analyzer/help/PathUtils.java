package org.wltea.analyzer.help;

import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Created by zhengfq on 2017/5/12.
 */

//@SuppressForbidden(reason = "accesses the default filesystem by design")
public final class PathUtils {
    /** no instantiation */
    private PathUtils() {}

    /** the actual JDK default */
    static final FileSystem ACTUAL_DEFAULT = FileSystems.getDefault();

    /** can be changed by tests */
    static volatile FileSystem DEFAULT = ACTUAL_DEFAULT;

    /**
     * Returns a {@code Path} from name components.
     * <p>
     * This works just like {@code Paths.get()}.
     * Remember: just like {@code Paths.get()} this is NOT A STRING CONCATENATION
     * UTILITY FUNCTION.
     * <p>
     * Remember: this should almost never be used. Usually resolve
     * a path against an existing one!
     */
    public static Path get(String first, String... more) {
        return DEFAULT.getPath(first, more);
    }

    /**
     * Returns a {@code Path} from a URI
     * <p>
     * This works just like {@code Paths.get()}.
     * <p>
     * Remember: this should almost never be used. Usually resolve
     * a path against an existing one!
     */
    public static Path get(URI uri) {
        if (uri.getScheme().equalsIgnoreCase("file")) {
            return DEFAULT.provider().getPath(uri);
        } else {
            return Paths.get(uri);
        }
    }

    /**
     * Tries to resolve the given path against the list of available roots.
     *
     * If path starts with one of the listed roots, it returned back by this method, otherwise null is returned.
     */
    public static Path get(Path[] roots, String path) {
        for (Path root : roots) {
            Path normalizedRoot = root.normalize();
            Path normalizedPath = normalizedRoot.resolve(path).normalize();
            if(normalizedPath.startsWith(normalizedRoot)) {
                return normalizedPath;
            }
        }
        return null;
    }

    /**
     * Tries to resolve the given file uri against the list of available roots.
     *
     * If uri starts with one of the listed roots, it returned back by this method, otherwise null is returned.
     */
    public static Path get(Path[] roots, URI uri) {
        return get(roots, PathUtils.get(uri).normalize().toString());
    }

    /**
     * Returns the default FileSystem.
     */
    public static FileSystem getDefaultFileSystem() {
        return DEFAULT;
    }
}
