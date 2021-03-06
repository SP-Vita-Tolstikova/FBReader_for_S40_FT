
package org.albite.io.decoders;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author albus
 */

/*
 Copyright 2013 Sole Proprietorship Vita Tolstikova
 
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

* Changes and additions to the author's code:
* --------------------------------------------
* 1. Minor changes and simplification in the source code
* 
*  
* support website: http://software.avt.dn.ua
*
* support email address: support@software.avt.dn.ua
*  
*/

abstract class AlbiteCharacterDecoder {

    public static final int     DECODING_DONE       = -1;

    /**
     * Used as a substitute, if there is a char that couldn't be read
     * right.
     */
    public static final char    SUBSTITUTE_CHAR     = '?';

    public abstract int decode(InputStream in) throws IOException;

    public static AlbiteCharacterDecoder getDecoder(final String encoding)
            throws UnsupportedEncodingException {

        
        /*
         * ASCII
         */
        if (match(encoding, Encodings.ASCII_ALIASES)) {
            return DecoderASCII.getInstance();
        }
        

        /*
         * UTF-8
         */
        if (match(encoding, Encodings.UTF_8_ALIASES)) {
            return DecoderUTF_8.getInstance();
        }

        /*
         * ISO-8859-X
         */
        if (match(encoding, Encodings.ISO_8859_1_ALIASES)) {
            return DecoderISO_8859_1.getInstance();
        }

       
        if (match(encoding, Encodings.ISO_8859_2_ALIASES)) {
            return DecoderISO_8859_2.getInstance();
        }

        if (match(encoding, Encodings.ISO_8859_3_ALIASES)) {
            return DecoderISO_8859_3.getInstance();
        }

        if (match(encoding, Encodings.ISO_8859_4_ALIASES)) {
            return DecoderISO_8859_4.getInstance();
        }

        if (match(encoding, Encodings.ISO_8859_5_ALIASES)) {
            return DecoderISO_8859_5.getInstance();
        }

        if (match(encoding, Encodings.ISO_8859_7_ALIASES)) {
            return DecoderISO_8859_7.getInstance();
        }

        if (match(encoding, Encodings.ISO_8859_9_ALIASES)) {
            return DecoderISO_8859_9.getInstance();
        }

        if (match(encoding, Encodings.ISO_8859_10_ALIASES)) {
            return DecoderISO_8859_10.getInstance();
        }

        if (match(encoding, Encodings.ISO_8859_13_ALIASES)) {
            return DecoderISO_8859_13.getInstance();
        }

        if (match(encoding, Encodings.ISO_8859_14_ALIASES)) {
            return DecoderISO_8859_14.getInstance();
        }

        if (match(encoding, Encodings.ISO_8859_15_ALIASES)) {
            return DecoderISO_8859_15.getInstance();
        }

        if (match(encoding, Encodings.ISO_8859_16_ALIASES)) {
            return DecoderISO_8859_16.getInstance();
        }
        

        /*
         * Windows
         */
        if (match(encoding, Encodings.WINDOWS_1250_ALIASES)) {
            return DecoderWindows_1250.getInstance();
        }

        
        if (match(encoding, Encodings.WINDOWS_1251_ALIASES)) {
            return DecoderWindows_1251.getInstance();
        }

        if (match(encoding, Encodings.WINDOWS_1252_ALIASES)) {
            return DecoderWindows_1252.getInstance();
        }

        if (match(encoding, Encodings.WINDOWS_1253_ALIASES)) {
            return DecoderWindows_1253.getInstance();
        }

        if (match(encoding, Encodings.WINDOWS_1254_ALIASES)) {
            return DecoderWindows_1254.getInstance();
        }

        if (match(encoding, Encodings.WINDOWS_1257_ALIASES)) {
            return DecoderWindows_1257.getInstance();
        }

        /*
         * KOI8
         */
        if (match(encoding, Encodings.KOI8_R_ALIASES)) {
            return DecoderKOI8_R.getInstance();
        }

        if (match(encoding, Encodings.KOI8_RU_ALIASES)) {
            return DecoderKOI8_RU.getInstance();
        }

        if (match(encoding, Encodings.KOI8_U_ALIASES)) {
            return DecoderKOI8_U.getInstance();
        }
       

        throw new UnsupportedEncodingException();
    }

    private static boolean match(final String name, final String[] aliases) {
        for (int i = 0; i < aliases.length; i++) {
            if (aliases[i].equalsIgnoreCase(name)) {
                return true;
            }
        }

        return false;
    }

    public static boolean decoderAvailable(final String encoding) {
        for (int i = 0; i < Encodings.ALIASES.length; i++) {
            if (match(encoding, Encodings.ALIASES[i])) {
                return true;
            }
        }

        return false;
    }

    public abstract String getEncoding();
}