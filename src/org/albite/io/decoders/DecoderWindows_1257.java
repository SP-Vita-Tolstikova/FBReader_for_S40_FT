
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
class DecoderWindows_1257 extends SingleByteDecoder {

    private static DecoderWindows_1257 instance;

    private static final short[] MAP = {
        /* 0x80 */
        0x20ac, (short) 0xfffd, 0x201a, (short) 0xfffd, 0x201e, 0x2026, 0x2020, 0x2021,
        (short) 0xfffd, 0x2030, (short) 0xfffd, 0x2039, (short) 0xfffd, 0x00a8, 0x02c7, 0x00b8,
        /* 0x90 */
        (short) 0xfffd, 0x2018, 0x2019, 0x201c, 0x201d, 0x2022, 0x2013, 0x2014,
        (short) 0xfffd, 0x2122, (short) 0xfffd, 0x203a, (short) 0xfffd, 0x00af, 0x02db, (short) 0xfffd,
        /* 0xa0 */
        0x00a0,(short)  0xfffd, 0x00a2, 0x00a3, 0x00a4, (short) 0xfffd, 0x00a6, 0x00a7,
        0x00d8, 0x00a9, 0x0156, 0x00ab, 0x00ac, 0x00ad, 0x00ae, 0x00c6,
        /* 0xb0 */
        0x00b0, 0x00b1, 0x00b2, 0x00b3, 0x00b4, 0x00b5, 0x00b6, 0x00b7,
        0x00f8, 0x00b9, 0x0157, 0x00bb, 0x00bc, 0x00bd, 0x00be, 0x00e6,
        /* 0xc0 */
        0x0104, 0x012e, 0x0100, 0x0106, 0x00c4, 0x00c5, 0x0118, 0x0112,
        0x010c, 0x00c9, 0x0179, 0x0116, 0x0122, 0x0136, 0x012a, 0x013b,
        /* 0xd0 */
        0x0160, 0x0143, 0x0145, 0x00d3, 0x014c, 0x00d5, 0x00d6, 0x00d7,
        0x0172, 0x0141, 0x015a, 0x016a, 0x00dc, 0x017b, 0x017d, 0x00df,
        /* 0xe0 */
        0x0105, 0x012f, 0x0101, 0x0107, 0x00e4, 0x00e5, 0x0119, 0x0113,
        0x010d, 0x00e9, 0x017a, 0x0117, 0x0123, 0x0137, 0x012b, 0x013c,
        /* 0xf0 */
        0x0161, 0x0144, 0x0146, 0x00f3, 0x014d, 0x00f5, 0x00f6, 0x00f7,
        0x0173, 0x0142, 0x015b, 0x016b, 0x00fc, 0x017c, 0x017e, 0x02d9,
    };

    private DecoderWindows_1257() {}

    public static AlbiteCharacterDecoder getInstance() {
        if (instance == null) {
            instance = new DecoderWindows_1257();
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
        return Encodings.WINDOWS_1257;
    }
}