
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

class DecoderISO_8859_4 extends SingleByteDecoder {

    private static DecoderISO_8859_4 instance;

    private static final short[] MAP = {
        /* 0xa0 */
        0x00a0, 0x0104, 0x0138, 0x0156, 0x00a4, 0x0128, 0x013b, 0x00a7,
        0x00a8, 0x0160, 0x0112, 0x0122, 0x0166, 0x00ad, 0x017d, 0x00af,
        /* 0xb0 */
        0x00b0, 0x0105, 0x02db, 0x0157, 0x00b4, 0x0129, 0x013c, 0x02c7,
        0x00b8, 0x0161, 0x0113, 0x0123, 0x0167, 0x014a, 0x017e, 0x014b,
        /* 0xc0 */
        0x0100, 0x00c1, 0x00c2, 0x00c3, 0x00c4, 0x00c5, 0x00c6, 0x012e,
        0x010c, 0x00c9, 0x0118, 0x00cb, 0x0116, 0x00cd, 0x00ce, 0x012a,
        /* 0xd0 */
        0x0110, 0x0145, 0x014c, 0x0136, 0x00d4, 0x00d5, 0x00d6, 0x00d7,
        0x00d8, 0x0172, 0x00da, 0x00db, 0x00dc, 0x0168, 0x016a, 0x00df,
        /* 0xe0 */
        0x0101, 0x00e1, 0x00e2, 0x00e3, 0x00e4, 0x00e5, 0x00e6, 0x012f,
        0x010d, 0x00e9, 0x0119, 0x00eb, 0x0117, 0x00ed, 0x00ee, 0x012b,
        /* 0xf0 */
        0x0111, 0x0146, 0x014d, 0x0137, 0x00f4, 0x00f5, 0x00f6, 0x00f7,
        0x00f8, 0x0173, 0x00fa, 0x00fb, 0x00fc, 0x0169, 0x016b, 0x02d9,
    };

    private DecoderISO_8859_4() {}

    public static AlbiteCharacterDecoder getInstance() {
        if (instance == null) {
            instance = new DecoderISO_8859_4();
        }
        return instance;
    }

    public final int decode(int code) {
        if (code < 0xa0) {
            return code;
        } else {
            return MAP[code - 0xA0];
        }
    }

    public final String getEncoding() {
        return Encodings.ISO_8859_4;
    }
}