
/* line 1 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */
;(function() {


/* line 117 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */



/* line 11 "js/lib/gherkin/lexer/he.js" */
const _lexer_actions = [
	0, 1, 0, 1, 1, 1, 2, 1, 
	3, 1, 4, 1, 5, 1, 6, 1, 
	7, 1, 8, 1, 9, 1, 10, 1, 
	11, 1, 14, 1, 15, 1, 16, 1, 
	17, 1, 18, 1, 19, 1, 20, 1, 
	21, 2, 2, 16, 2, 11, 0, 2, 
	12, 13, 2, 15, 0, 2, 15, 1, 
	2, 15, 14, 2, 15, 17, 2, 16, 
	4, 2, 16, 5, 2, 16, 6, 2, 
	16, 7, 2, 16, 8, 2, 16, 14, 
	2, 18, 19, 2, 20, 0, 2, 20, 
	1, 2, 20, 14, 2, 20, 17, 3, 
	3, 12, 13, 3, 9, 12, 13, 3, 
	10, 12, 13, 3, 11, 12, 13, 3, 
	12, 13, 16, 3, 15, 12, 13, 4, 
	2, 12, 13, 16, 4, 15, 0, 12, 
	13
];

const _lexer_key_offsets = [
	0, 0, 12, 19, 20, 22, 23, 24, 
	25, 26, 28, 39, 40, 41, 45, 50, 
	55, 60, 65, 69, 73, 75, 76, 77, 
	78, 79, 80, 81, 82, 83, 84, 85, 
	86, 87, 88, 89, 90, 95, 102, 107, 
	111, 117, 120, 122, 128, 139, 141, 142, 
	143, 144, 145, 146, 147, 148, 149, 150, 
	151, 152, 153, 154, 155, 156, 157, 158, 
	159, 160, 161, 162, 163, 164, 165, 166, 
	167, 174, 176, 178, 180, 182, 184, 186, 
	188, 190, 192, 194, 205, 206, 207, 208, 
	209, 210, 211, 212, 213, 214, 215, 216, 
	217, 218, 219, 220, 221, 222, 231, 237, 
	239, 242, 244, 246, 248, 251, 253, 255, 
	257, 259, 261, 263, 265, 267, 269, 271, 
	273, 275, 277, 279, 281, 283, 285, 287, 
	289, 291, 293, 295, 299, 301, 303, 305, 
	307, 309, 311, 313, 315, 317, 319, 321, 
	323, 325, 327, 329, 331, 333, 335, 337, 
	339, 341, 343, 345, 347, 349, 351, 353, 
	355, 357, 359, 361, 363, 365, 367, 369, 
	371, 373, 375, 376, 379, 380, 381, 382, 
	383, 384, 385, 386, 387, 388, 389, 390, 
	391, 392, 393, 394, 395, 396, 397, 398, 
	399, 408, 414, 416, 419, 421, 423, 425, 
	428, 430, 432, 434, 436, 438, 440, 442, 
	444, 446, 448, 450, 452, 454, 456, 458, 
	460, 462, 464, 466, 468, 470, 472, 475, 
	477, 479, 481, 483, 485, 487, 489, 491, 
	493, 495, 497, 499, 501, 503, 505, 507, 
	509, 511, 513, 515, 517, 519, 521, 523, 
	525, 527, 529, 530, 531, 532, 533, 534, 
	535, 536, 537, 538, 546, 550, 552, 554, 
	556, 558, 560, 562, 564, 566, 568, 570, 
	572, 574, 576, 578, 580, 582, 584, 586, 
	590, 592, 594, 596, 598, 600, 602, 604, 
	606, 608, 610, 612, 614, 616, 618, 620, 
	622, 624, 626, 628, 630, 632, 634, 636, 
	638, 640, 642, 644, 646, 648, 650, 652, 
	654, 656, 658, 660, 662, 664, 665, 666, 
	667, 668, 669, 670, 671, 672, 673, 682, 
	689, 691, 694, 696, 698, 700, 703, 705, 
	707, 709, 711, 713, 715, 717, 719, 721, 
	723, 725, 727, 729, 731, 733, 735, 737, 
	739, 741, 743, 745, 747, 749, 751, 753, 
	755, 757, 761, 763, 765, 767, 769, 771, 
	773, 775, 777, 779, 781, 783, 785, 787, 
	789, 791, 793, 795, 797, 799, 801, 803, 
	805, 807, 809, 811, 813, 815, 817, 819, 
	821, 823, 825, 827, 829, 831, 833, 835, 
	836, 837
];

const _lexer_trans_keys = [
	-41, 10, 32, 34, 35, 37, 42, 64, 
	124, 239, 9, 13, -112, -111, -109, -107, 
	-101, -88, -86, -41, -111, -106, -41, -100, 
	32, 10, 10, 13, -41, 10, 32, 34, 
	35, 37, 42, 64, 124, 9, 13, 34, 
	34, 10, 32, 9, 13, 10, 32, 34, 
	9, 13, 10, 32, 34, 9, 13, 10, 
	32, 34, 9, 13, 10, 32, 34, 9, 
	13, 10, 32, 9, 13, 10, 32, 9, 
	13, 10, 13, 10, 95, 70, 69, 65, 
	84, 85, 82, 69, 95, 69, 78, 68, 
	95, 37, 13, 32, 64, 9, 10, 9, 
	10, 13, 32, 64, 11, 12, 10, 32, 
	64, 9, 13, 32, 124, 9, 13, 10, 
	32, 92, 124, 9, 13, 10, 92, 124, 
	10, 92, 10, 32, 92, 124, 9, 13, 
	-41, 10, 32, 34, 35, 37, 42, 64, 
	124, 9, 13, -41, 32, -103, -41, -108, 
	-41, -103, -41, -96, -41, -86, -41, -97, 
	-41, -107, -41, -110, -41, -98, -41, -112, 
	-41, -107, -41, -86, 58, 10, 10, -41, 
	10, 32, 35, 124, 9, 13, -86, 10, 
	-41, 10, -101, 10, -41, 10, -107, 10, 
	-41, 10, -96, 10, -41, 10, -108, 10, 
	10, 58, -41, 10, 32, 34, 35, 37, 
	42, 64, 124, 9, 13, -41, -110, -41, 
	-99, -41, -112, -41, -87, -41, -88, -41, 
	-89, -41, -94, 58, 10, 10, -41, 10, 
	32, 35, 37, 42, 64, 9, 13, -112, 
	-111, -107, -101, -86, 10, -41, 10, -111, 
	-106, 10, -41, 10, -100, 10, 10, 32, 
	-41, 10, 32, -103, 10, -41, 10, -108, 
	10, -41, 10, -103, 10, -41, 10, -96, 
	10, -41, 10, -86, 10, -41, 10, -97, 
	10, -41, 10, -110, 10, -41, 10, -99, 
	10, -41, 10, -112, 10, -41, 10, -87, 
	10, -41, 10, -88, 10, -41, 10, -111, 
	-101, -88, 10, -41, 10, -96, 10, -41, 
	10, -103, 10, -41, 10, -86, 10, 10, 
	32, -41, 10, -86, 10, -41, 10, -88, 
	10, -41, 10, -105, 10, -41, 10, -103, 
	10, -41, 10, -87, 10, 10, 58, -41, 
	10, -107, 10, -41, 10, -96, 10, -41, 
	10, -108, 10, 10, 95, 10, 70, 10, 
	69, 10, 65, 10, 84, 10, 85, 10, 
	82, 10, 69, 10, 95, 10, 69, 10, 
	78, 10, 68, 10, 95, 10, 37, -41, 
	-111, -101, -88, -41, -96, -41, -103, -41, 
	-86, 32, -41, -86, -41, -88, -41, -105, 
	-41, -103, -41, -87, 58, 10, 10, -41, 
	10, 32, 35, 37, 42, 64, 9, 13, 
	-112, -111, -107, -101, -86, 10, -41, 10, 
	-111, -106, 10, -41, 10, -100, 10, 10, 
	32, -41, 10, 32, -103, 10, -41, 10, 
	-108, 10, -41, 10, -103, 10, -41, 10, 
	-96, 10, -41, 10, -86, 10, -41, 10, 
	-97, 10, -41, 10, -110, 10, -41, 10, 
	-99, 10, -41, 10, -112, 10, -41, 10, 
	-87, 10, -41, 10, -88, 10, -41, 10, 
	-101, -88, 10, -41, 10, -107, 10, -41, 
	10, -96, 10, -41, 10, -108, 10, 10, 
	58, -41, 10, -105, 10, -41, 10, -103, 
	10, -41, 10, -87, 10, 10, 95, 10, 
	70, 10, 69, 10, 65, 10, 84, 10, 
	85, 10, 82, 10, 69, 10, 95, 10, 
	69, 10, 78, 10, 68, 10, 95, 10, 
	37, -41, -107, -41, -96, -41, -108, 58, 
	10, 10, -41, 10, 32, 35, 37, 64, 
	9, 13, -109, -88, -86, 10, -41, 10, 
	-107, 10, -41, 10, -110, 10, -41, 10, 
	-98, 10, -41, 10, -112, 10, -41, 10, 
	-107, 10, -41, 10, -86, 10, 10, 58, 
	-41, 10, -89, 10, -41, 10, -94, 10, 
	-41, 10, -111, -101, -88, 10, -41, 10, 
	-96, 10, -41, 10, -103, 10, -41, 10, 
	-86, 10, 10, 32, -41, 10, -86, 10, 
	-41, 10, -88, 10, -41, 10, -105, 10, 
	-41, 10, -103, 10, -41, 10, -87, 10, 
	-41, 10, -107, 10, -41, 10, -96, 10, 
	-41, 10, -108, 10, 10, 95, 10, 70, 
	10, 69, 10, 65, 10, 84, 10, 85, 
	10, 82, 10, 69, 10, 95, 10, 69, 
	10, 78, 10, 68, 10, 95, 10, 37, 
	-41, -105, -41, -103, -41, -87, 58, 10, 
	10, -41, 10, 32, 35, 37, 42, 64, 
	9, 13, -112, -111, -107, -101, -88, -86, 
	10, -41, 10, -111, -106, 10, -41, 10, 
	-100, 10, 10, 32, -41, 10, 32, -103, 
	10, -41, 10, -108, 10, -41, 10, -103, 
	10, -41, 10, -96, 10, -41, 10, -86, 
	10, -41, 10, -97, 10, -41, 10, -110, 
	10, -41, 10, -99, 10, -41, 10, -112, 
	10, -41, 10, -87, 10, -41, 10, -88, 
	10, -41, 10, -89, 10, -41, 10, -94, 
	10, 10, 58, -41, 10, -111, -101, -88, 
	10, -41, 10, -96, 10, -41, 10, -103, 
	10, -41, 10, -86, 10, 10, 32, -41, 
	10, -86, 10, -41, 10, -88, 10, -41, 
	10, -105, 10, -41, 10, -103, 10, -41, 
	10, -87, 10, -41, 10, -107, 10, -41, 
	10, -96, 10, -41, 10, -108, 10, 10, 
	95, 10, 70, 10, 69, 10, 65, 10, 
	84, 10, 85, 10, 82, 10, 69, 10, 
	95, 10, 69, 10, 78, 10, 68, 10, 
	95, 10, 37, 187, 191, 0
];

const _lexer_single_lengths = [
	0, 10, 7, 1, 2, 1, 1, 1, 
	1, 2, 9, 1, 1, 2, 3, 3, 
	3, 3, 2, 2, 2, 1, 1, 1, 
	1, 1, 1, 1, 1, 1, 1, 1, 
	1, 1, 1, 1, 3, 5, 3, 2, 
	4, 3, 2, 4, 9, 2, 1, 1, 
	1, 1, 1, 1, 1, 1, 1, 1, 
	1, 1, 1, 1, 1, 1, 1, 1, 
	1, 1, 1, 1, 1, 1, 1, 1, 
	5, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 9, 1, 1, 1, 1, 
	1, 1, 1, 1, 1, 1, 1, 1, 
	1, 1, 1, 1, 1, 7, 6, 2, 
	3, 2, 2, 2, 3, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 4, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 1, 3, 1, 1, 1, 1, 
	1, 1, 1, 1, 1, 1, 1, 1, 
	1, 1, 1, 1, 1, 1, 1, 1, 
	7, 6, 2, 3, 2, 2, 2, 3, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 3, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 1, 1, 1, 1, 1, 1, 
	1, 1, 1, 6, 4, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 4, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 1, 1, 1, 
	1, 1, 1, 1, 1, 1, 7, 7, 
	2, 3, 2, 2, 2, 3, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 4, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 2, 
	2, 2, 2, 2, 2, 2, 2, 1, 
	1, 0
];

const _lexer_range_lengths = [
	0, 1, 0, 0, 0, 0, 0, 0, 
	0, 0, 1, 0, 0, 1, 1, 1, 
	1, 1, 1, 1, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 1, 1, 1, 1, 
	1, 0, 0, 1, 1, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	1, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 1, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 1, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	1, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 1, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 1, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0
];

const _lexer_index_offsets = [
	0, 0, 12, 20, 22, 25, 27, 29, 
	31, 33, 36, 47, 49, 51, 55, 60, 
	65, 70, 75, 79, 83, 86, 88, 90, 
	92, 94, 96, 98, 100, 102, 104, 106, 
	108, 110, 112, 114, 116, 121, 128, 133, 
	137, 143, 147, 150, 156, 167, 170, 172, 
	174, 176, 178, 180, 182, 184, 186, 188, 
	190, 192, 194, 196, 198, 200, 202, 204, 
	206, 208, 210, 212, 214, 216, 218, 220, 
	222, 229, 232, 235, 238, 241, 244, 247, 
	250, 253, 256, 259, 270, 272, 274, 276, 
	278, 280, 282, 284, 286, 288, 290, 292, 
	294, 296, 298, 300, 302, 304, 313, 320, 
	323, 327, 330, 333, 336, 340, 343, 346, 
	349, 352, 355, 358, 361, 364, 367, 370, 
	373, 376, 379, 382, 385, 388, 391, 394, 
	397, 400, 403, 406, 411, 414, 417, 420, 
	423, 426, 429, 432, 435, 438, 441, 444, 
	447, 450, 453, 456, 459, 462, 465, 468, 
	471, 474, 477, 480, 483, 486, 489, 492, 
	495, 498, 501, 504, 507, 510, 513, 516, 
	519, 522, 525, 527, 531, 533, 535, 537, 
	539, 541, 543, 545, 547, 549, 551, 553, 
	555, 557, 559, 561, 563, 565, 567, 569, 
	571, 580, 587, 590, 594, 597, 600, 603, 
	607, 610, 613, 616, 619, 622, 625, 628, 
	631, 634, 637, 640, 643, 646, 649, 652, 
	655, 658, 661, 664, 667, 670, 673, 677, 
	680, 683, 686, 689, 692, 695, 698, 701, 
	704, 707, 710, 713, 716, 719, 722, 725, 
	728, 731, 734, 737, 740, 743, 746, 749, 
	752, 755, 758, 760, 762, 764, 766, 768, 
	770, 772, 774, 776, 784, 789, 792, 795, 
	798, 801, 804, 807, 810, 813, 816, 819, 
	822, 825, 828, 831, 834, 837, 840, 843, 
	848, 851, 854, 857, 860, 863, 866, 869, 
	872, 875, 878, 881, 884, 887, 890, 893, 
	896, 899, 902, 905, 908, 911, 914, 917, 
	920, 923, 926, 929, 932, 935, 938, 941, 
	944, 947, 950, 953, 956, 959, 961, 963, 
	965, 967, 969, 971, 973, 975, 977, 986, 
	994, 997, 1001, 1004, 1007, 1010, 1014, 1017, 
	1020, 1023, 1026, 1029, 1032, 1035, 1038, 1041, 
	1044, 1047, 1050, 1053, 1056, 1059, 1062, 1065, 
	1068, 1071, 1074, 1077, 1080, 1083, 1086, 1089, 
	1092, 1095, 1100, 1103, 1106, 1109, 1112, 1115, 
	1118, 1121, 1124, 1127, 1130, 1133, 1136, 1139, 
	1142, 1145, 1148, 1151, 1154, 1157, 1160, 1163, 
	1166, 1169, 1172, 1175, 1178, 1181, 1184, 1187, 
	1190, 1193, 1196, 1199, 1202, 1205, 1208, 1211, 
	1213, 1215
];

const _lexer_indicies = [
	1, 3, 2, 4, 5, 6, 7, 8, 
	9, 10, 2, 0, 11, 12, 13, 14, 
	15, 16, 17, 0, 18, 0, 19, 20, 
	0, 21, 0, 22, 0, 23, 0, 0, 
	24, 26, 27, 25, 1, 3, 2, 4, 
	5, 6, 7, 8, 9, 2, 0, 28, 
	0, 29, 0, 30, 29, 29, 0, 33, 
	32, 34, 32, 31, 37, 36, 38, 36, 
	35, 37, 36, 39, 36, 35, 37, 36, 
	40, 36, 35, 42, 41, 41, 0, 3, 
	43, 43, 0, 45, 46, 44, 3, 0, 
	47, 0, 48, 0, 49, 0, 50, 0, 
	51, 0, 52, 0, 53, 0, 54, 0, 
	55, 0, 56, 0, 57, 0, 58, 0, 
	59, 0, 60, 0, 0, 0, 0, 0, 
	61, 62, 63, 62, 62, 65, 64, 61, 
	3, 66, 8, 66, 0, 67, 68, 67, 
	0, 71, 70, 72, 73, 70, 69, 0, 
	75, 76, 74, 0, 75, 74, 71, 77, 
	75, 76, 77, 74, 78, 71, 79, 80, 
	81, 82, 83, 84, 85, 79, 0, 86, 
	23, 0, 22, 0, 87, 0, 88, 0, 
	89, 0, 90, 0, 91, 0, 92, 0, 
	93, 0, 94, 0, 95, 0, 22, 0, 
	96, 0, 97, 0, 98, 0, 99, 0, 
	100, 0, 101, 0, 102, 0, 103, 0, 
	104, 0, 105, 0, 106, 0, 107, 0, 
	108, 0, 110, 109, 112, 111, 113, 112, 
	114, 115, 115, 114, 111, 116, 112, 111, 
	117, 112, 111, 118, 112, 111, 119, 112, 
	111, 120, 112, 111, 121, 112, 111, 122, 
	112, 111, 123, 112, 111, 124, 112, 111, 
	112, 125, 111, 126, 128, 127, 129, 130, 
	131, 132, 133, 134, 127, 0, 135, 0, 
	136, 0, 137, 0, 22, 0, 138, 0, 
	139, 0, 140, 0, 141, 0, 142, 0, 
	22, 0, 143, 0, 144, 0, 145, 0, 
	146, 0, 147, 0, 149, 148, 151, 150, 
	152, 151, 153, 154, 155, 156, 154, 153, 
	150, 157, 158, 159, 160, 161, 151, 150, 
	162, 151, 150, 163, 164, 151, 150, 165, 
	151, 150, 166, 151, 150, 151, 167, 150, 
	168, 151, 167, 150, 166, 151, 150, 169, 
	151, 150, 170, 151, 150, 171, 151, 150, 
	172, 151, 150, 173, 151, 150, 174, 151, 
	150, 175, 151, 150, 176, 151, 150, 177, 
	151, 150, 166, 151, 150, 178, 151, 150, 
	179, 151, 150, 180, 151, 150, 166, 151, 
	150, 181, 151, 150, 182, 151, 150, 183, 
	151, 150, 184, 151, 150, 185, 151, 150, 
	166, 151, 150, 186, 151, 150, 187, 188, 
	189, 151, 150, 190, 151, 150, 191, 151, 
	150, 192, 151, 150, 193, 151, 150, 194, 
	151, 150, 195, 151, 150, 151, 196, 150, 
	197, 151, 150, 198, 151, 150, 199, 151, 
	150, 189, 151, 150, 200, 151, 150, 201, 
	151, 150, 202, 151, 150, 203, 151, 150, 
	204, 151, 150, 205, 151, 150, 151, 167, 
	150, 206, 151, 150, 207, 151, 150, 208, 
	151, 150, 209, 151, 150, 210, 151, 150, 
	205, 151, 150, 151, 211, 150, 151, 212, 
	150, 151, 213, 150, 151, 214, 150, 151, 
	215, 150, 151, 216, 150, 151, 217, 150, 
	151, 218, 150, 151, 219, 150, 151, 220, 
	150, 151, 221, 150, 151, 222, 150, 151, 
	223, 150, 151, 224, 150, 225, 0, 226, 
	227, 228, 0, 229, 0, 230, 0, 231, 
	0, 232, 0, 233, 0, 234, 0, 235, 
	0, 236, 0, 237, 0, 238, 0, 239, 
	0, 240, 0, 241, 0, 242, 0, 243, 
	0, 244, 0, 245, 0, 246, 0, 248, 
	247, 250, 249, 251, 250, 252, 253, 254, 
	255, 253, 252, 249, 256, 257, 258, 259, 
	260, 250, 249, 261, 250, 249, 262, 263, 
	250, 249, 264, 250, 249, 265, 250, 249, 
	250, 266, 249, 267, 250, 266, 249, 265, 
	250, 249, 268, 250, 249, 269, 250, 249, 
	270, 250, 249, 271, 250, 249, 272, 250, 
	249, 273, 250, 249, 274, 250, 249, 275, 
	250, 249, 276, 250, 249, 265, 250, 249, 
	277, 250, 249, 278, 250, 249, 279, 250, 
	249, 265, 250, 249, 280, 250, 249, 281, 
	250, 249, 282, 250, 249, 283, 250, 249, 
	284, 250, 249, 265, 250, 249, 285, 250, 
	249, 286, 287, 250, 249, 288, 250, 249, 
	289, 250, 249, 290, 250, 249, 291, 250, 
	249, 292, 250, 249, 293, 250, 249, 250, 
	266, 249, 294, 250, 249, 295, 250, 249, 
	296, 250, 249, 297, 250, 249, 298, 250, 
	249, 293, 250, 249, 250, 299, 249, 250, 
	300, 249, 250, 301, 249, 250, 302, 249, 
	250, 303, 249, 250, 304, 249, 250, 305, 
	249, 250, 306, 249, 250, 307, 249, 250, 
	308, 249, 250, 309, 249, 250, 310, 249, 
	250, 311, 249, 250, 312, 249, 313, 0, 
	314, 0, 315, 0, 316, 0, 317, 0, 
	318, 0, 319, 0, 321, 320, 323, 322, 
	324, 323, 325, 326, 327, 326, 325, 322, 
	328, 329, 330, 323, 322, 331, 323, 322, 
	332, 323, 322, 333, 323, 322, 334, 323, 
	322, 335, 323, 322, 336, 323, 322, 337, 
	323, 322, 338, 323, 322, 339, 323, 322, 
	340, 323, 322, 341, 323, 322, 342, 323, 
	322, 323, 343, 322, 344, 323, 322, 345, 
	323, 322, 346, 323, 322, 342, 323, 322, 
	347, 323, 322, 348, 349, 350, 323, 322, 
	351, 323, 322, 352, 323, 322, 353, 323, 
	322, 354, 323, 322, 355, 323, 322, 356, 
	323, 322, 323, 357, 322, 358, 323, 322, 
	359, 323, 322, 360, 323, 322, 350, 323, 
	322, 361, 323, 322, 362, 323, 322, 363, 
	323, 322, 364, 323, 322, 365, 323, 322, 
	342, 323, 322, 366, 323, 322, 367, 323, 
	322, 368, 323, 322, 369, 323, 322, 370, 
	323, 322, 342, 323, 322, 323, 371, 322, 
	323, 372, 322, 323, 373, 322, 323, 374, 
	322, 323, 375, 322, 323, 376, 322, 323, 
	377, 322, 323, 378, 322, 323, 379, 322, 
	323, 380, 322, 323, 381, 322, 323, 382, 
	322, 323, 383, 322, 323, 384, 322, 385, 
	0, 386, 0, 387, 0, 388, 0, 389, 
	0, 390, 0, 391, 0, 393, 392, 395, 
	394, 396, 395, 397, 398, 399, 400, 398, 
	397, 394, 401, 402, 403, 404, 405, 406, 
	395, 394, 407, 395, 394, 408, 409, 395, 
	394, 410, 395, 394, 411, 395, 394, 395, 
	412, 394, 413, 395, 412, 394, 411, 395, 
	394, 414, 395, 394, 415, 395, 394, 416, 
	395, 394, 417, 395, 394, 418, 395, 394, 
	419, 395, 394, 420, 395, 394, 421, 395, 
	394, 422, 395, 394, 411, 395, 394, 423, 
	395, 394, 424, 395, 394, 425, 395, 394, 
	411, 395, 394, 426, 395, 394, 427, 395, 
	394, 428, 395, 394, 429, 395, 394, 430, 
	395, 394, 411, 395, 394, 431, 395, 394, 
	432, 395, 394, 433, 395, 394, 434, 395, 
	394, 395, 412, 394, 435, 395, 394, 436, 
	437, 438, 395, 394, 439, 395, 394, 440, 
	395, 394, 441, 395, 394, 442, 395, 394, 
	443, 395, 394, 444, 395, 394, 395, 445, 
	394, 446, 395, 394, 447, 395, 394, 448, 
	395, 394, 438, 395, 394, 449, 395, 394, 
	450, 395, 394, 451, 395, 394, 452, 395, 
	394, 453, 395, 394, 434, 395, 394, 454, 
	395, 394, 455, 395, 394, 456, 395, 394, 
	457, 395, 394, 458, 395, 394, 434, 395, 
	394, 395, 459, 394, 395, 460, 394, 395, 
	461, 394, 395, 462, 394, 395, 463, 394, 
	395, 464, 394, 395, 465, 394, 395, 466, 
	394, 395, 467, 394, 395, 468, 394, 395, 
	469, 394, 395, 470, 394, 395, 471, 394, 
	395, 472, 394, 473, 0, 2, 0, 474, 
	0
];

const _lexer_trans_targs = [
	0, 2, 10, 10, 11, 20, 22, 7, 
	36, 39, 399, 3, 47, 57, 84, 88, 
	94, 170, 4, 5, 45, 6, 7, 8, 
	9, 9, 10, 21, 12, 13, 14, 15, 
	15, 15, 16, 15, 15, 15, 16, 17, 
	18, 19, 10, 19, 20, 10, 21, 23, 
	24, 25, 26, 27, 28, 29, 30, 31, 
	32, 33, 34, 35, 401, 37, 38, 10, 
	37, 36, 38, 39, 40, 41, 43, 44, 
	42, 40, 41, 42, 40, 43, 2, 44, 
	11, 20, 22, 7, 36, 39, 46, 48, 
	49, 50, 51, 52, 53, 54, 55, 56, 
	58, 59, 60, 61, 62, 63, 64, 65, 
	66, 67, 68, 69, 70, 71, 72, 71, 
	72, 73, 72, 10, 74, 75, 76, 77, 
	78, 79, 80, 81, 82, 83, 2, 10, 
	10, 11, 20, 22, 7, 36, 39, 85, 
	86, 87, 89, 90, 91, 92, 93, 95, 
	96, 97, 98, 99, 100, 101, 100, 101, 
	102, 101, 10, 156, 107, 103, 110, 120, 
	124, 130, 104, 105, 108, 106, 107, 83, 
	109, 111, 112, 113, 114, 115, 116, 117, 
	118, 119, 121, 122, 123, 125, 126, 127, 
	128, 129, 131, 132, 150, 143, 133, 134, 
	135, 136, 137, 138, 139, 140, 141, 142, 
	144, 145, 146, 147, 148, 149, 151, 152, 
	153, 154, 155, 157, 158, 159, 160, 161, 
	162, 163, 164, 165, 166, 167, 168, 169, 
	10, 171, 172, 250, 317, 173, 174, 175, 
	176, 177, 178, 179, 180, 181, 182, 183, 
	184, 185, 186, 187, 188, 189, 190, 191, 
	192, 191, 192, 193, 192, 10, 236, 198, 
	194, 201, 211, 215, 221, 195, 196, 199, 
	197, 198, 83, 200, 202, 203, 204, 205, 
	206, 207, 208, 209, 210, 212, 213, 214, 
	216, 217, 218, 219, 220, 222, 223, 230, 
	224, 225, 226, 227, 228, 229, 231, 232, 
	233, 234, 235, 237, 238, 239, 240, 241, 
	242, 243, 244, 245, 246, 247, 248, 249, 
	10, 251, 252, 253, 254, 255, 256, 257, 
	258, 259, 258, 259, 260, 259, 10, 303, 
	261, 274, 278, 262, 263, 264, 265, 266, 
	267, 268, 269, 270, 271, 272, 273, 83, 
	275, 276, 277, 279, 280, 297, 291, 281, 
	282, 283, 284, 285, 286, 287, 288, 289, 
	290, 292, 293, 294, 295, 296, 298, 299, 
	300, 301, 302, 304, 305, 306, 307, 308, 
	309, 310, 311, 312, 313, 314, 315, 316, 
	10, 318, 319, 320, 321, 322, 323, 324, 
	325, 326, 325, 326, 327, 326, 10, 385, 
	332, 328, 335, 345, 349, 355, 360, 329, 
	330, 333, 331, 332, 83, 334, 336, 337, 
	338, 339, 340, 341, 342, 343, 344, 346, 
	347, 348, 350, 351, 352, 353, 354, 356, 
	357, 358, 359, 361, 362, 379, 373, 363, 
	364, 365, 366, 367, 368, 369, 370, 371, 
	372, 374, 375, 376, 377, 378, 380, 381, 
	382, 383, 384, 386, 387, 388, 389, 390, 
	391, 392, 393, 394, 395, 396, 397, 398, 
	10, 400, 0
];

const _lexer_trans_actions = [
	39, 25, 0, 47, 3, 1, 0, 25, 
	1, 31, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	50, 0, 99, 19, 0, 0, 47, 5, 
	41, 119, 41, 0, 29, 111, 29, 29, 
	0, 7, 95, 0, 0, 103, 21, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 23, 107, 
	23, 44, 0, 0, 0, 33, 33, 47, 
	33, 80, 0, 0, 35, 0, 89, 0, 
	86, 83, 37, 89, 83, 92, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 50, 124, 0, 
	47, 77, 0, 74, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 17, 56, 27, 
	115, 53, 50, 27, 56, 50, 59, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 50, 124, 0, 47, 
	77, 0, 65, 29, 77, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 11, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	11, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 50, 
	124, 0, 47, 77, 0, 71, 29, 77, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 15, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	15, 0, 0, 0, 0, 0, 0, 0, 
	50, 124, 0, 47, 77, 0, 62, 29, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 9, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	9, 0, 0, 0, 0, 0, 0, 0, 
	50, 124, 0, 47, 77, 0, 68, 29, 
	77, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 13, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	0, 0, 0, 0, 0, 0, 0, 0, 
	13, 0, 0
];

const _lexer_eof_actions = [
	0, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39, 39, 39, 39, 39, 39, 39, 
	39, 39
];

const lexer_start = 1;
const lexer_first_final = 401;
const lexer_error = 0;

const lexer_en_main = 1;


/* line 120 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

/* line 121 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

/* line 122 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

var Lexer = function(listener) {
  // Check that listener has the required functions
  var events = ['comment', 'tag', 'feature', 'background', 'scenario', 'scenario_outline', 'examples', 'step', 'py_string', 'row', 'eof'];
  for(e in events) {
    var event = events[e];
    if(typeof listener[event] != 'function') {
      "Error. No " + event + " function exists on " + JSON.stringify(listener);
    }
  }
  this.listener = listener;  
};

Lexer.prototype.scan = function(data) {
  var ending = "\n%_FEATURE_END_%";
  if(typeof data == 'string') {
    data = this.stringToBytes(data + ending);
  } else if(typeof Buffer != 'undefined' && Buffer.isBuffer(data)) {
    // Node.js
    var buf = new Buffer(data.length + ending.length);
    data.copy(buf, 0, 0);
    new Buffer(ending).copy(buf, data.length, 0);
    data = buf;
  }
  var eof = pe = data.length;
  var p = 0;

  this.line_number = 1;
  this.last_newline = 0;

  
/* line 735 "js/lib/gherkin/lexer/he.js" */
{
	  this.cs = lexer_start;
} /* JSCodeGen::writeInit */

/* line 153 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */
  
/* line 742 "js/lib/gherkin/lexer/he.js" */
{
	var _klen, _trans, _keys, _ps, _widec, _acts, _nacts;
	var _goto_level, _resume, _eof_trans, _again, _test_eof;
	var _out;
	_klen = _trans = _keys = _acts = _nacts = null;
	_goto_level = 0;
	_resume = 10;
	_eof_trans = 15;
	_again = 20;
	_test_eof = 30;
	_out = 40;
	while (true) {
	_trigger_goto = false;
	if (_goto_level <= 0) {
	if (p == pe) {
		_goto_level = _test_eof;
		continue;
	}
	if ( this.cs == 0) {
		_goto_level = _out;
		continue;
	}
	}
	if (_goto_level <= _resume) {
	_keys = _lexer_key_offsets[ this.cs];
	_trans = _lexer_index_offsets[ this.cs];
	_klen = _lexer_single_lengths[ this.cs];
	_break_match = false;
	
	do {
	  if (_klen > 0) {
	     _lower = _keys;
	     _upper = _keys + _klen - 1;

	     while (true) {
	        if (_upper < _lower) { break; }
	        _mid = _lower + ( (_upper - _lower) >> 1 );

	        if ( data[p] < _lexer_trans_keys[_mid]) {
	           _upper = _mid - 1;
	        } else if ( data[p] > _lexer_trans_keys[_mid]) {
	           _lower = _mid + 1;
	        } else {
	           _trans += (_mid - _keys);
	           _break_match = true;
	           break;
	        };
	     } /* while */
	     if (_break_match) { break; }
	     _keys += _klen;
	     _trans += _klen;
	  }
	  _klen = _lexer_range_lengths[ this.cs];
	  if (_klen > 0) {
	     _lower = _keys;
	     _upper = _keys + (_klen << 1) - 2;
	     while (true) {
	        if (_upper < _lower) { break; }
	        _mid = _lower + (((_upper-_lower) >> 1) & ~1);
	        if ( data[p] < _lexer_trans_keys[_mid]) {
	          _upper = _mid - 2;
	         } else if ( data[p] > _lexer_trans_keys[_mid+1]) {
	          _lower = _mid + 2;
	        } else {
	          _trans += ((_mid - _keys) >> 1);
	          _break_match = true;
	          break;
	        }
	     } /* while */
	     if (_break_match) { break; }
	     _trans += _klen
	  }
	} while (false);
	_trans = _lexer_indicies[_trans];
	 this.cs = _lexer_trans_targs[_trans];
	if (_lexer_trans_actions[_trans] != 0) {
		_acts = _lexer_trans_actions[_trans];
		_nacts = _lexer_actions[_acts];
		_acts += 1;
		while (_nacts > 0) {
			_nacts -= 1;
			_acts += 1;
			switch (_lexer_actions[_acts - 1]) {
case 0:
/* line 6 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    this.content_start = p;
    this.current_line = this.line_number;
    this.start_col = p - this.last_newline - (this.keyword+':').length;
  		break;
case 1:
/* line 12 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    this.current_line = this.line_number;
    this.start_col = p - this.last_newline;
  		break;
case 2:
/* line 17 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    this.content_start = p;
  		break;
case 3:
/* line 21 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    var con = this.unindent(
      this.start_col, 
      this.bytesToString(data.slice(this.content_start, this.next_keyword_start-1)).replace(/(\r?\n)?([\t ])*$/, '').replace(/\\\"\\\"\\\"/mg, '"""')
    );
    this.listener.py_string(con, this.current_line); 
  		break;
case 4:
/* line 29 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    p = this.store_keyword_content('feature', data, p, eof);
  		break;
case 5:
/* line 33 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    p = this.store_keyword_content('background', data, p, eof);
  		break;
case 6:
/* line 37 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    p = this.store_keyword_content('scenario', data, p, eof);
  		break;
case 7:
/* line 41 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    p = this.store_keyword_content('scenario_outline', data, p, eof);
  		break;
case 8:
/* line 45 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    p = this.store_keyword_content('examples', data, p, eof);
  		break;
case 9:
/* line 49 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    var con = this.bytesToString(data.slice(this.content_start, p)).trim();
    this.listener.step(this.keyword, con, this.current_line);
  		break;
case 10:
/* line 54 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    var con = this.bytesToString(data.slice(this.content_start, p)).trim();
    this.listener.comment(con, this.line_number);
    this.keyword_start = null;
  		break;
case 11:
/* line 60 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    var con = this.bytesToString(data.slice(this.content_start, p)).trim();
    this.listener.tag(con, this.line_number);
    this.keyword_start = null;
  		break;
case 12:
/* line 66 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    this.line_number++;
  		break;
case 13:
/* line 70 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    this.last_newline = p + 1;
  		break;
case 14:
/* line 74 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    this.keyword_start = this.keyword_start || p;
  		break;
case 15:
/* line 78 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    this.keyword = this.bytesToString(data.slice(this.keyword_start, p)).replace(/:$/, '');
    this.keyword_start = null;
  		break;
case 16:
/* line 83 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    this.next_keyword_start = p;
  		break;
case 17:
/* line 87 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    p = p - 1;
    current_row = [];
    this.current_line = this.line_number;
  		break;
case 18:
/* line 93 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    this.content_start = p;
  		break;
case 19:
/* line 97 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    var con = this.bytesToString(data.slice(this.content_start, p)).trim();
    current_row.push(con.replace(/\\\|/, "|").replace(/\\n/, "\n").replace(/\\\\/, "\\"));
  		break;
case 20:
/* line 102 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    this.listener.row(current_row, this.current_line);
  		break;
case 21:
/* line 106 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    if(this.cs < lexer_first_final) {
      var content = this.current_line_content(data, p);
      throw "Lexing error on line " + this.line_number + ": '" + content + "'. See http://wiki.github.com/cucumber/gherkin/lexingerror for more information.";
    } else {
      this.listener.eof();
    }
    
  		break;
/* line 958 "js/lib/gherkin/lexer/he.js" */
			} /* action switch */
		}
	}
	if (_trigger_goto) {
		continue;
	}
	}
	if (_goto_level <= _again) {
	if ( this.cs == 0) {
		_goto_level = _out;
		continue;
	}
	p += 1;
	if (p != pe) {
		_goto_level = _resume;
		continue;
	}
	}
	if (_goto_level <= _test_eof) {
	if (p == eof) {
	__acts = _lexer_eof_actions[ this.cs];
	__nacts =  _lexer_actions[__acts];
	__acts += 1;
	while (__nacts > 0) {
		__nacts -= 1;
		__acts += 1;
		switch (_lexer_actions[__acts - 1]) {
case 21:
/* line 106 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */

    if(this.cs < lexer_first_final) {
      var content = this.current_line_content(data, p);
      throw "Lexing error on line " + this.line_number + ": '" + content + "'. See http://wiki.github.com/cucumber/gherkin/lexingerror for more information.";
    } else {
      this.listener.eof();
    }
    
  		break;
/* line 997 "js/lib/gherkin/lexer/he.js" */
		} /* eof action switch */
	}
	if (_trigger_goto) {
		continue;
	}
}
	}
	if (_goto_level <= _out) {
		break;
	}
	}
	}

/* line 154 "/Users/ahellesoy/scm/gherkin/tasks/../ragel/i18n/he.js.rl" */
};

Lexer.prototype.bytesToString = function(bytes) {
  if(typeof bytes.write == 'function') {
    // Node.js
    return bytes.toString('utf-8');
  } else {
    var result = "";
    for(var b in bytes) {
      result += String.fromCharCode(bytes[b]);
    }
    return result;
  }
};

Lexer.prototype.stringToBytes = function(string) {
  var bytes = [];
  for(var i = 0; i < string.length; i++) {
    bytes[i] = string.charCodeAt(i);
  }
  return bytes;
};

Lexer.prototype.unindent = function(startcol, text) {
  startcol = startcol || 0;
  return text.replace(new RegExp('^[\t ]{0,' + startcol + '}', 'gm'), ''); 
};

Lexer.prototype.store_keyword_content = function(event, data, p, eof) {
  var end_point = (!this.next_keyword_start || (p == eof)) ? p : this.next_keyword_start;
  var content = this.unindent(this.start_col + 2, this.bytesToString(data.slice(this.content_start, end_point))).trimRight();
  var content_lines = content.split("\n")
  var name = content_lines.shift() || "";
  name = name.trim();
  var description = content_lines.join("\n");
  this.listener[event](this.keyword, name, description, this.current_line);
  var nks = this.next_keyword_start;
  this.next_keyword_start = null;
  return nks ? nks - 1 : p;
};

Lexer.prototype.current_line_content = function(data, p) {
  var rest = data.slice(this.last_newline, -1);
  var end = rest.indexOf(10) || -1;
  return this.bytesToString(rest.slice(0, end)).trim();
};

if(typeof exports != 'undefined') {
  exports.Lexer = Lexer;
}
if(typeof window != 'undefined') {
  window.Lexer = Lexer;
}

})();
