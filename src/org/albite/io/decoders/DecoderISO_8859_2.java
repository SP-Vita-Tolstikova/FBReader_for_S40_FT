
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

class DecoderISO_8859_2 extends SingleByteDecoder {

    private static DecoderISO_8859_2 instance;

    private static final short[] MAP = {
        /* 0xa0 */
        0x00a0, 0x0104, 0x02d8, 0x0141, 0x00a4, 0x013d, 0x015a, 0x00a7,
        0x00a8, 0x0160, 0x015e, 0x0164, 0x0179, 0x00ad, 0x017d, 0x017b,
        /* 0xb0 */
        0x00b0, 0x0105, 0x02db, 0x0142, 0x00b4, 0x013e, 0x015b, 0x02c7,
        0x00b8, 0x0161, 0x015f, 0x0165, 0x017a, 0x02dd, 0x017e, 0x017c,
        /* 0xc0 */
        0x0154, 0x00c1, 0x00c2, 0x0102, 0x00c4, 0x0139, 0x0106, 0x00c7,
        0x010c, 0x00c9, 0x0118, 0x00cb, 0x011a, 0x00cd, 0x00ce, 0x010e,
        /* 0xd0 */
        0x0110, 0x0143, 0x0147, 0x00d3, 0x00d4, 0x0150, 0x00d6, 0x00d7,
        0x0158, 0x016e, 0x00da, 0x0170, 0x00dc, 0x00dd, 0x0162, 0x00df,
        /* 0xe0 */
        0x0155, 0x00e1, 0x00e2, 0x0103, 0x00e4, 0x013a, 0x0107, 0x00e7,
        0x010d, 0x00e9, 0x0119, 0x00eb, 0x011b, 0x00ed, 0x00ee, 0x010f,
        /* 0xf0 */
        0x0111, 0x0144, 0x0148, 0x00f3, 0x00f4, 0x0151, 0x00f6, 0x00f7,
        0x0159, 0x016f, 0x00fa, 0x0171, 0x00fc, 0x00fd, 0x0163, 0x02d9,
    };

    private DecoderISO_8859_2() {}

    public static AlbiteCharacterDecoder getInstance() {
        if (instance == null) {
            instance = new DecoderISO_8859_2();
        }
        return instance;
    }

    public final int decode(int code) {
        if (code < 0xa0) {
            return code;
        } else {
            return MAP[code - 0xa0];
        }
    }

    public final String getEncoding() {
        return Encodings.ISO_8859_2;
    }
}