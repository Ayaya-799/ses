/*
 * $Id: Debug.java,v 1.3 2001/10/20 17:46:07 gmurray Exp $
 * Copyright 2001 Sun Microsystems, Inc. All rights reserved.
 * Copyright 2001 Sun Microsystems, Inc. Tous droits r�serv�s.
 */

package icbc.cmis.tags;

/**
 * This class is just a helper class to make it handy
 * to print out debug statements
 */
public final class Debug {

    public static final boolean debuggingOn = true;

    public static void issert(boolean condition) {
        if (!condition) {
            println("Assert Failed: ");
            throw new IllegalArgumentException();
        }
    }

    public static void print(String msg) {
        if (debuggingOn) {
            System.out.print(msg);
        }
    }

    public static void println(String msg) {
        if (debuggingOn) {
            System.out.println(">>" + msg);
        }
    }

    public static void print(Exception e, String msg) {
        print((Throwable)e, msg);
    }

    public static void print(Exception e) {
        print(e, null);
    }

    public static void print(Throwable t, String msg) {
        if (debuggingOn) {
            System.out.println("Received throwable with Message: "+t.getMessage());
            if (msg != null)
                System.out.print(msg);
            t.printStackTrace();
        }
    }

    public static void print(Throwable t) {
        print(t, null);
    }
}
