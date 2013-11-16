
package org.albite.io.decoders;

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

class DecoderWindows_1253 extends SingleByteDecoder {

    private static DecoderWindows_1253 instance;

    private static final short[] MAP = {
        /* 0x80 */
        0x20ac, (short) 0xfffd, 0x201a, 0x0192, 0x201e, 0x2026, 0x2020, 0x2021,
        (short) 0xfffd, 0x2030, (short) 0xfffd, 0x2039, (short) 0xfffd, (short) 0xfffd, (short) 0xfffd, (short) 0xfffd,
        /* 0x90 */
        (short) 0xfffd, 0x2018, 0x2019, 0x201c, 0x201d, 0x2022, 0x2013, 0x2014,
        (short) 0xfffd, 0x2122, (short) 0xfffd, 0x203a, (short) 0xfffd, (short) 0xfffd, (short) 0xfffd, (short) 0xfffd,
        /* 0xa0 */
        0x00a0, 0x0385, 0x0386, 0x00a3, 0x00a4, 0x00a5, 0x00a6, 0x00a7,
        0x00a8, 0x00a9, (short) 0xfffd, 0x00ab, 0x00ac, 0x00ad, 0x00ae, 0x2015,
        /* 0xb0 */
        0x00b0, 0x00b1, 0x00b2, 0x00b3, 0x0384, 0x00b5, 0x00b6, 0x00b7,
        0x0388, 0x0389, 0x038a, 0x00bb, 0x038c, 0x00bd, 0x038e, 0x038f,
        /* 0xc0 */
        0x0390, 0x0391, 0x0392, 0x0393, 0x0394, 0x0395, 0x0396, 0x0397,
        0x0398, 0x0399, 0x039a, 0x039b, 0x039c, 0x039d, 0x039e, 0x039f,
        /* 0xd0 */
        0x03a0, 0x03a1, (short) 0xfffd, 0x03a3, 0x03a4, 0x03a5, 0x03a6, 0x03a7,
        0x03a8, 0x03a9, 0x03aa, 0x03ab, 0x03ac, 0x03ad, 0x03ae, 0x03af,
        /* 0xe0 */
        0x03b0, 0x03b1, 0x03b2, 0x03b3, 0x03b4, 0x03b5, 0x03b6, 0x03b7,
        0x03b8, 0x03b9, 0x03ba, 0x03bb, 0x03bc, 0x03bd, 0x03be, 0x03bf,
        /* 0xf0 */
        0x03c0, 0x03c1, 0x03c2, 0x03c3, 0x03c4, 0x03c5, 0x03c6, 0x03c7,
        (short) 0x03c8, 0x03c9, 0x03ca, 0x03cb, 0x03cc, 0x03cd, 0x03ce, (short) 0xfffd,
    };

    private DecoderWindows_1253() {}

    public static AlbiteCharacterDecoder getInstance() {
        if (instance == null) {
            instance = new DecoderWindows_1253();
        }
        return instance;
    }

    public final int decode(int code) {
        if (code < 0x80) {
            return code;
        } else {
            code = MAP[code - 0x80] & 0xFFFF;
            if (code == 0xFFFD) {
                return SUBSTITUTE_CHAR;
            } else {
                return code;
            }
        }
    }

    public final String getEncoding() {
        return Encodings.WINDOWS_1253;
    }
}