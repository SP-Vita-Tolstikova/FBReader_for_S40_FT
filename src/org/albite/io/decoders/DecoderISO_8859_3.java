
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

class DecoderISO_8859_3 extends SingleByteDecoder {

    private static DecoderISO_8859_3 instance;

    private static final short[] MAP = {
        /* 0xa0 */
        0x00a0, 0x0126, 0x02d8, 0x00a3, 0x00a4, (short) 0xfffd, 0x0124, 0x00a7,
        0x00a8, 0x0130, 0x015e, 0x011e, 0x0134, 0x00ad, (short) 0xfffd, 0x017b,
        /* 0xb0 */
        0x00b0, 0x0127, 0x00b2, 0x00b3, 0x00b4, 0x00b5, 0x0125, 0x00b7,
        0x00b8, 0x0131, 0x015f, 0x011f, 0x0135, 0x00bd, (short)0xfffd, 0x017c,
        /* 0xc0 */
        0x00c0, 0x00c1, 0x00c2, (short)0xfffd, 0x00c4, 0x010a, 0x0108, 0x00c7,
        0x00c8, 0x00c9, 0x00ca, 0x00cb, 0x00cc, 0x00cd, 0x00ce, 0x00cf,
        /* 0xd0 */
        (short)0xfffd, 0x00d1, 0x00d2, 0x00d3, 0x00d4, 0x0120, 0x00d6, 0x00d7,
        0x011c, 0x00d9, 0x00da, 0x00db, 0x00dc, 0x016c, 0x015c, 0x00df,
        /* 0xe0 */
        0x00e0, 0x00e1, 0x00e2, (short)0xfffd, 0x00e4, 0x010b, 0x0109, 0x00e7,
        0x00e8, 0x00e9, 0x00ea, 0x00eb, 0x00ec, 0x00ed, 0x00ee, 0x00ef,
        /* 0xf0 */
        (short)0xfffd, 0x00f1, 0x00f2, 0x00f3, 0x00f4, 0x0121, 0x00f6, 0x00f7,
        0x011d, 0x00f9, 0x00fa, 0x00fb, 0x00fc, 0x016d, 0x015d, 0x02d9,
    };

    private DecoderISO_8859_3() {}

    public static AlbiteCharacterDecoder getInstance() {
        if (instance == null) {
            instance = new DecoderISO_8859_3();
        }
        return instance;
    }

    public final int decode(int code) {
        if (code < 0xa0) {
            return code;
        } else {
            code = MAP[code - 0xa0] & 0xFFFF;
            if (code == 0xFFFD) {
                return SUBSTITUTE_CHAR;
            } else {
                return code;
            }
        }
    }

    public final String getEncoding() {
        return Encodings.ISO_8859_3;
    }
}