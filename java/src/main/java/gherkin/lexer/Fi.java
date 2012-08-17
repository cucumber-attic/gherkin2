
// line 1 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
package gherkin.lexer;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import gherkin.lexer.Lexer;
import gherkin.lexer.Listener;
import gherkin.lexer.LexingError;

public class Fi implements Lexer {
  
// line 150 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"


  private final Listener listener;

  public Fi(Listener listener) {
    this.listener = listener;
  }

  
// line 26 "java/src/main/java/gherkin/lexer/Fi.java"
private static byte[] init__lexer_actions_0()
{
	return new byte [] {
	    0,    1,    0,    1,    1,    1,    2,    1,    3,    1,    4,    1,
	    5,    1,    6,    1,    7,    1,    8,    1,    9,    1,   10,    1,
	   11,    1,   12,    1,   13,    1,   16,    1,   17,    1,   18,    1,
	   19,    1,   20,    1,   21,    1,   22,    1,   23,    2,    2,   18,
	    2,    3,    4,    2,   13,    0,    2,   14,   15,    2,   17,    0,
	    2,   17,    1,    2,   17,   16,    2,   17,   19,    2,   18,    6,
	    2,   18,    7,    2,   18,    8,    2,   18,    9,    2,   18,   10,
	    2,   18,   16,    2,   20,   21,    2,   22,    0,    2,   22,    1,
	    2,   22,   16,    2,   22,   19,    3,    4,   14,   15,    3,    5,
	   14,   15,    3,   11,   14,   15,    3,   12,   14,   15,    3,   13,
	   14,   15,    3,   14,   15,   18,    3,   17,    0,   11,    3,   17,
	   14,   15,    4,    2,   14,   15,   18,    4,    3,    4,   14,   15,
	    4,   17,    0,   14,   15,    5,   17,    0,   11,   14,   15
	};
}

private static final byte _lexer_actions[] = init__lexer_actions_0();


private static short[] init__lexer_key_offsets_0()
{
	return new short [] {
	    0,    0,   17,   18,   19,   35,   36,   37,   39,   41,   46,   51,
	   56,   61,   65,   69,   71,   72,   73,   74,   75,   76,   77,   78,
	   79,   80,   81,   82,   83,   84,   85,   86,   87,   89,   91,   96,
	  103,  108,  109,  110,  111,  112,  113,  114,  115,  116,  118,  119,
	  120,  121,  122,  123,  124,  125,  126,  127,  128,  129,  130,  131,
	  132,  133,  134,  135,  144,  146,  148,  150,  152,  154,  156,  158,
	  160,  162,  164,  166,  168,  170,  172,  174,  176,  178,  180,  182,
	  184,  186,  188,  190,  192,  208,  209,  211,  212,  213,  215,  216,
	  217,  218,  219,  220,  221,  228,  230,  232,  234,  236,  238,  240,
	  242,  244,  246,  248,  250,  251,  252,  266,  268,  270,  272,  274,
	  276,  278,  280,  282,  284,  286,  288,  290,  292,  294,  296,  298,
	  300,  302,  304,  306,  308,  310,  312,  315,  317,  319,  321,  323,
	  325,  327,  329,  331,  333,  335,  337,  339,  341,  343,  345,  347,
	  350,  352,  354,  356,  359,  361,  363,  365,  367,  369,  371,  373,
	  374,  375,  376,  377,  378,  379,  380,  394,  396,  398,  400,  402,
	  404,  406,  408,  410,  412,  414,  416,  418,  420,  422,  424,  426,
	  428,  430,  432,  434,  436,  438,  440,  443,  445,  447,  449,  451,
	  453,  455,  457,  459,  461,  463,  465,  467,  469,  471,  473,  475,
	  477,  479,  480,  481,  482,  483,  484,  485,  499,  501,  503,  505,
	  507,  509,  511,  513,  515,  517,  519,  521,  523,  525,  527,  529,
	  531,  533,  535,  537,  539,  541,  543,  545,  548,  550,  552,  554,
	  556,  558,  560,  562,  564,  566,  568,  570,  572,  574,  576,  578,
	  580,  582,  584,  586,  588,  591,  593,  595,  597,  599,  603,  609,
	  612,  614,  620,  636,  638,  641,  643,  645,  648,  650,  652,  654,
	  657,  659,  661,  663,  665,  667,  669,  671
	};
}

private static final short _lexer_key_offsets[] = init__lexer_key_offsets_0();


private static byte[] init__lexer_trans_keys_0()
{
	return new byte [] {
	  -17,   10,   32,   34,   35,   37,   42,   64,   74,   75,   77,   78,
	   79,   84,  124,    9,   13,  -69,  -65,   10,   32,   34,   35,   37,
	   42,   64,   74,   75,   77,   78,   79,   84,  124,    9,   13,   34,
	   34,   10,   13,   10,   13,   10,   32,   34,    9,   13,   10,   32,
	   34,    9,   13,   10,   32,   34,    9,   13,   10,   32,   34,    9,
	   13,   10,   32,    9,   13,   10,   32,    9,   13,   10,   13,   10,
	   95,   70,   69,   65,   84,   85,   82,   69,   95,   69,   78,   68,
	   95,   37,   32,   10,   13,   10,   13,   13,   32,   64,    9,   10,
	    9,   10,   13,   32,   64,   11,   12,   10,   32,   64,    9,   13,
	   97,  117,  110,  117,  116,  116,  105,  105,  108,  109,  101,  116,
	  101,  116,   97,   97,  105,  110,   97,  105,  115,  117,  117,  115,
	   58,   10,   10,   10,   32,   35,   37,   64,   79,   84,    9,   13,
	   10,   95,   10,   70,   10,   69,   10,   65,   10,   84,   10,   85,
	   10,   82,   10,   69,   10,   95,   10,   69,   10,   78,   10,   68,
	   10,   95,   10,   37,   10,  109,   10,  105,   10,  110,   10,   97,
	   10,  105,   10,  115,   10,  117,   10,  117,   10,  115,   10,   58,
	   10,   32,   34,   35,   37,   42,   64,   74,   75,   77,   78,   79,
	   84,  124,    9,   13,   97,  112,  117,   97,  117,  107,  115,  115,
	  101,  116,   58,   10,   10,   10,   32,   35,   79,  124,    9,   13,
	   10,  109,   10,  105,   10,  110,   10,   97,   10,  105,   10,  115,
	   10,  117,   10,  117,   10,  115,   10,   58,   58,   97,   10,   10,
	   10,   32,   35,   37,   42,   64,   74,   75,   77,   78,   79,   84,
	    9,   13,   10,   95,   10,   70,   10,   69,   10,   65,   10,   84,
	   10,   85,   10,   82,   10,   69,   10,   95,   10,   69,   10,   78,
	   10,   68,   10,   95,   10,   37,   10,   32,   10,   97,   10,  117,
	   10,  110,   10,  117,   10,  116,   10,  116,   10,  105,   10,  105,
	   10,  108,  109,   10,  101,   10,  116,   10,  101,   10,  116,   10,
	   97,   10,   97,   10,  105,   10,  110,   10,   97,   10,  105,   10,
	  115,   10,  117,   10,  117,   10,  115,   10,   58,   10,   97,   10,
	  112,  117,   10,   97,   10,  117,   10,  115,   10,   58,   97,   10,
	  105,   10,  104,   10,  105,   10,  111,   10,  115,   10,  116,   10,
	   97,  105,  104,  105,  111,   58,   10,   10,   10,   32,   35,   37,
	   42,   64,   74,   75,   77,   78,   79,   84,    9,   13,   10,   95,
	   10,   70,   10,   69,   10,   65,   10,   84,   10,   85,   10,   82,
	   10,   69,   10,   95,   10,   69,   10,   78,   10,   68,   10,   95,
	   10,   37,   10,   32,   10,   97,   10,  117,   10,  110,   10,  117,
	   10,  116,   10,  116,   10,  105,   10,  105,   10,  108,  109,   10,
	  101,   10,  116,   10,  101,   10,  116,   10,   97,   10,   97,   10,
	  105,   10,  110,   10,   97,   10,  105,   10,  115,   10,  117,   10,
	  117,   10,  115,   10,   58,   10,   97,   10,  112,   10,   97,  115,
	  116,   97,   58,   10,   10,   10,   32,   35,   37,   42,   64,   74,
	   75,   77,   78,   79,   84,    9,   13,   10,   95,   10,   70,   10,
	   69,   10,   65,   10,   84,   10,   85,   10,   82,   10,   69,   10,
	   95,   10,   69,   10,   78,   10,   68,   10,   95,   10,   37,   10,
	   32,   10,   97,   10,  117,   10,  110,   10,  117,   10,  116,   10,
	  116,   10,  105,   10,  105,   10,  108,  109,   10,  101,   10,  116,
	   10,  101,   10,  116,   10,   97,   10,   97,   10,  105,   10,  110,
	   10,   97,   10,  105,   10,  115,   10,  117,   10,  117,   10,  115,
	   10,   58,   10,   97,   10,  112,   10,   97,   10,  117,   10,  115,
	   10,   58,   97,   10,  105,   10,  104,   10,  105,   10,  111,   32,
	  124,    9,   13,   10,   32,   92,  124,    9,   13,   10,   92,  124,
	   10,   92,   10,   32,   92,  124,    9,   13,   10,   32,   34,   35,
	   37,   42,   64,   74,   75,   77,   78,   79,   84,  124,    9,   13,
	   10,   97,   10,  112,  117,   10,   97,   10,  117,   10,  107,  115,
	   10,  115,   10,  101,   10,  116,   10,   58,   97,   10,  105,   10,
	  104,   10,  105,   10,  111,   10,  115,   10,  116,   10,   97,    0
	};
}

private static final byte _lexer_trans_keys[] = init__lexer_trans_keys_0();


private static byte[] init__lexer_single_lengths_0()
{
	return new byte [] {
	    0,   15,    1,    1,   14,    1,    1,    2,    2,    3,    3,    3,
	    3,    2,    2,    2,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,    1,    1,    1,    1,    1,    1,    2,    2,    3,    5,
	    3,    1,    1,    1,    1,    1,    1,    1,    1,    2,    1,    1,
	    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
	    1,    1,    1,    7,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,   14,    1,    2,    1,    1,    2,    1,    1,
	    1,    1,    1,    1,    5,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    1,    1,   12,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    3,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    3,
	    2,    2,    2,    3,    2,    2,    2,    2,    2,    2,    2,    1,
	    1,    1,    1,    1,    1,    1,   12,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    3,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    1,    1,    1,    1,    1,    1,   12,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    3,    2,    2,    2,    2,
	    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
	    2,    2,    2,    2,    3,    2,    2,    2,    2,    2,    4,    3,
	    2,    4,   14,    2,    3,    2,    2,    3,    2,    2,    2,    3,
	    2,    2,    2,    2,    2,    2,    2,    0
	};
}

private static final byte _lexer_single_lengths[] = init__lexer_single_lengths_0();


private static byte[] init__lexer_range_lengths_0()
{
	return new byte [] {
	    0,    1,    0,    0,    1,    0,    0,    0,    0,    1,    1,    1,
	    1,    1,    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    1,    1,
	    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    1,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    1,    1,    0,
	    0,    1,    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0
	};
}

private static final byte _lexer_range_lengths[] = init__lexer_range_lengths_0();


private static short[] init__lexer_index_offsets_0()
{
	return new short [] {
	    0,    0,   17,   19,   21,   37,   39,   41,   44,   47,   52,   57,
	   62,   67,   71,   75,   78,   80,   82,   84,   86,   88,   90,   92,
	   94,   96,   98,  100,  102,  104,  106,  108,  110,  113,  116,  121,
	  128,  133,  135,  137,  139,  141,  143,  145,  147,  149,  152,  154,
	  156,  158,  160,  162,  164,  166,  168,  170,  172,  174,  176,  178,
	  180,  182,  184,  186,  195,  198,  201,  204,  207,  210,  213,  216,
	  219,  222,  225,  228,  231,  234,  237,  240,  243,  246,  249,  252,
	  255,  258,  261,  264,  267,  283,  285,  288,  290,  292,  295,  297,
	  299,  301,  303,  305,  307,  314,  317,  320,  323,  326,  329,  332,
	  335,  338,  341,  344,  347,  349,  351,  365,  368,  371,  374,  377,
	  380,  383,  386,  389,  392,  395,  398,  401,  404,  407,  410,  413,
	  416,  419,  422,  425,  428,  431,  434,  438,  441,  444,  447,  450,
	  453,  456,  459,  462,  465,  468,  471,  474,  477,  480,  483,  486,
	  490,  493,  496,  499,  503,  506,  509,  512,  515,  518,  521,  524,
	  526,  528,  530,  532,  534,  536,  538,  552,  555,  558,  561,  564,
	  567,  570,  573,  576,  579,  582,  585,  588,  591,  594,  597,  600,
	  603,  606,  609,  612,  615,  618,  621,  625,  628,  631,  634,  637,
	  640,  643,  646,  649,  652,  655,  658,  661,  664,  667,  670,  673,
	  676,  679,  681,  683,  685,  687,  689,  691,  705,  708,  711,  714,
	  717,  720,  723,  726,  729,  732,  735,  738,  741,  744,  747,  750,
	  753,  756,  759,  762,  765,  768,  771,  774,  778,  781,  784,  787,
	  790,  793,  796,  799,  802,  805,  808,  811,  814,  817,  820,  823,
	  826,  829,  832,  835,  838,  842,  845,  848,  851,  854,  858,  864,
	  868,  871,  877,  893,  896,  900,  903,  906,  910,  913,  916,  919,
	  923,  926,  929,  932,  935,  938,  941,  944
	};
}

private static final short _lexer_index_offsets[] = init__lexer_index_offsets_0();


private static short[] init__lexer_indicies_0()
{
	return new short [] {
	    1,    3,    2,    4,    5,    6,    7,    8,    9,   10,   11,   12,
	   13,   14,   15,    2,    0,   16,    0,    2,    0,    3,    2,    4,
	    5,    6,    7,    8,    9,   10,   11,   12,   13,   14,   15,    2,
	    0,   17,    0,   18,    0,   20,   21,   19,   23,   24,   22,   27,
	   26,   28,   26,   25,   31,   30,   32,   30,   29,   31,   30,   33,
	   30,   29,   31,   30,   34,   30,   29,   36,   35,   35,    0,    3,
	   37,   37,    0,   39,   40,   38,    3,    0,   41,    0,   42,    0,
	   43,    0,   44,    0,   45,    0,   46,    0,   47,    0,   48,    0,
	   49,    0,   50,    0,   51,    0,   52,    0,   53,    0,   54,    0,
	   55,    0,   57,   58,   56,   60,   61,   59,    0,    0,    0,    0,
	   62,   63,   64,   63,   63,   66,   65,   62,    3,   67,    8,   67,
	    0,   68,    0,   69,    0,   68,    0,   70,    0,   71,    0,   72,
	    0,   73,    0,   69,    0,   74,   75,    0,   76,    0,   77,    0,
	   78,    0,   79,    0,   80,    0,   69,    0,   81,    0,   82,    0,
	   83,    0,   84,    0,   85,    0,   86,    0,   87,    0,   88,    0,
	   89,    0,   91,   90,   93,   92,   93,   94,   95,   96,   95,   97,
	   98,   94,   92,   93,   99,   92,   93,  100,   92,   93,  101,   92,
	   93,  102,   92,   93,  103,   92,   93,  104,   92,   93,  105,   92,
	   93,  106,   92,   93,  107,   92,   93,  108,   92,   93,  109,   92,
	   93,  110,   92,   93,  111,   92,   93,  112,   92,   93,  113,   92,
	   93,  114,   92,   93,  115,   92,   93,  116,   92,   93,  117,   92,
	   93,  118,   92,   93,  119,   92,   93,  120,   92,   93,  121,   92,
	   93,  122,   92,  124,  123,  125,  126,  127,  128,  129,  130,  131,
	  132,  133,  134,  135,  136,  123,    0,  137,    0,  138,  139,    0,
	  140,    0,  141,    0,  142,  143,    0,  144,    0,  145,    0,  146,
	    0,  147,    0,  149,  148,  151,  150,  151,  152,  153,  154,  153,
	  152,  150,  151,  155,  150,  151,  156,  150,  151,  157,  150,  151,
	  158,  150,  151,  159,  150,  151,  160,  150,  151,  161,  150,  151,
	  162,  150,  151,  163,  150,  151,  164,  150,  165,  166,    0,  168,
	  167,  170,  169,  170,  171,  172,  173,  174,  172,  175,  176,  177,
	  178,  179,  180,  171,  169,  170,  181,  169,  170,  182,  169,  170,
	  183,  169,  170,  184,  169,  170,  185,  169,  170,  186,  169,  170,
	  187,  169,  170,  188,  169,  170,  189,  169,  170,  190,  169,  170,
	  191,  169,  170,  192,  169,  170,  193,  169,  170,  194,  169,  170,
	  195,  169,  170,  196,  169,  170,  197,  169,  170,  196,  169,  170,
	  198,  169,  170,  199,  169,  170,  200,  169,  170,  201,  169,  170,
	  197,  169,  170,  202,  203,  169,  170,  204,  169,  170,  205,  169,
	  170,  206,  169,  170,  207,  169,  170,  208,  169,  170,  197,  169,
	  170,  209,  169,  170,  210,  169,  170,  211,  169,  170,  212,  169,
	  170,  213,  169,  170,  214,  169,  170,  215,  169,  170,  216,  169,
	  170,  195,  169,  170,  217,  169,  170,  218,  219,  169,  170,  220,
	  169,  170,  221,  169,  170,  222,  169,  170,  195,  223,  169,  170,
	  224,  169,  170,  225,  169,  170,  226,  169,  170,  216,  169,  170,
	  227,  169,  170,  228,  169,  170,  216,  169,  229,    0,  230,    0,
	  231,    0,  232,    0,  233,    0,  235,  234,  237,  236,  237,  238,
	  239,  240,  241,  239,  242,  243,  244,  245,  246,  247,  238,  236,
	  237,  248,  236,  237,  249,  236,  237,  250,  236,  237,  251,  236,
	  237,  252,  236,  237,  253,  236,  237,  254,  236,  237,  255,  236,
	  237,  256,  236,  237,  257,  236,  237,  258,  236,  237,  259,  236,
	  237,  260,  236,  237,  261,  236,  237,  262,  236,  237,  263,  236,
	  237,  264,  236,  237,  263,  236,  237,  265,  236,  237,  266,  236,
	  237,  267,  236,  237,  268,  236,  237,  264,  236,  237,  269,  270,
	  236,  237,  271,  236,  237,  272,  236,  237,  273,  236,  237,  274,
	  236,  237,  275,  236,  237,  264,  236,  237,  276,  236,  237,  277,
	  236,  237,  278,  236,  237,  279,  236,  237,  280,  236,  237,  281,
	  236,  237,  282,  236,  237,  283,  236,  237,  262,  236,  237,  284,
	  236,  237,  285,  236,  237,  281,  236,  286,    0,  287,    0,  288,
	    0,  289,    0,  291,  290,  293,  292,  293,  294,  295,  296,  297,
	  295,  298,  299,  300,  301,  302,  303,  294,  292,  293,  304,  292,
	  293,  305,  292,  293,  306,  292,  293,  307,  292,  293,  308,  292,
	  293,  309,  292,  293,  310,  292,  293,  311,  292,  293,  312,  292,
	  293,  313,  292,  293,  314,  292,  293,  315,  292,  293,  316,  292,
	  293,  317,  292,  293,  318,  292,  293,  319,  292,  293,  320,  292,
	  293,  319,  292,  293,  321,  292,  293,  322,  292,  293,  323,  292,
	  293,  324,  292,  293,  320,  292,  293,  325,  326,  292,  293,  327,
	  292,  293,  328,  292,  293,  329,  292,  293,  330,  292,  293,  331,
	  292,  293,  320,  292,  293,  332,  292,  293,  333,  292,  293,  334,
	  292,  293,  335,  292,  293,  336,  292,  293,  337,  292,  293,  338,
	  292,  293,  339,  292,  293,  318,  292,  293,  340,  292,  293,  341,
	  292,  293,  342,  292,  293,  343,  292,  293,  344,  292,  293,  318,
	  345,  292,  293,  346,  292,  293,  347,  292,  293,  348,  292,  293,
	  339,  292,  349,  350,  349,    0,  353,  352,  354,  355,  352,  351,
	    0,  357,  358,  356,    0,  357,  356,  353,  359,  357,  358,  359,
	  356,  353,  360,  361,  362,  363,  364,  365,  366,  367,  368,  369,
	  370,  371,  372,  360,    0,   93,  373,   92,   93,  374,  375,   92,
	   93,  376,   92,   93,  377,   92,   93,  378,  379,   92,   93,  380,
	   92,   93,  381,   92,   93,  121,   92,   93,  122,  382,   92,   93,
	  383,   92,   93,  384,   92,   93,  385,   92,   93,  121,   92,   93,
	  386,   92,   93,  387,   92,   93,  121,   92,  388,    0
	};
}

private static final short _lexer_indicies[] = init__lexer_indicies_0();


private static short[] init__lexer_trans_targs_0()
{
	return new short [] {
	    0,    2,    4,    4,    5,   15,   17,   31,   34,   37,   38,   40,
	   43,   45,   89,  273,    3,    6,    7,    8,    9,    8,    8,    9,
	    8,   10,   10,   10,   11,   10,   10,   10,   11,   12,   13,   14,
	    4,   14,   15,    4,   16,   18,   19,   20,   21,   22,   23,   24,
	   25,   26,   27,   28,   29,   30,  295,   32,   33,    4,   16,   33,
	    4,   16,   35,   36,    4,   35,   34,   36,   31,   39,   41,   42,
	   37,   44,   46,   52,   47,   48,   49,   50,   51,   53,   54,   55,
	   56,   57,   58,   59,   60,   61,   62,   63,   62,   63,   63,    4,
	   64,   78,  279,   65,   66,   67,   68,   69,   70,   71,   72,   73,
	   74,   75,   76,   77,    4,   79,   80,   81,   82,   83,   84,   85,
	   86,   87,   88,    4,    4,    5,   15,   17,   31,   34,   37,   38,
	   40,   43,   45,   89,  273,   90,   91,  217,   92,   93,   94,  111,
	   95,   96,   97,   98,   99,  100,   99,  100,  100,    4,  101,  102,
	  103,  104,  105,  106,  107,  108,  109,  110,   88,  112,  167,  113,
	  114,  113,  114,  114,    4,  115,  129,  130,  131,  133,  136,  138,
	  154,  116,  117,  118,  119,  120,  121,  122,  123,  124,  125,  126,
	  127,  128,    4,   88,  129,  132,  134,  135,  130,  137,  139,  145,
	  140,  141,  142,  143,  144,  146,  147,  148,  149,  150,  151,  152,
	  153,  155,  156,  164,  157,  158,  159,  160,  161,  162,  163,  165,
	  166,  168,  169,  170,  171,  172,  173,  174,  173,  174,  174,    4,
	  175,  189,  190,  191,  193,  196,  198,  214,  176,  177,  178,  179,
	  180,  181,  182,  183,  184,  185,  186,  187,  188,    4,   88,  189,
	  192,  194,  195,  190,  197,  199,  205,  200,  201,  202,  203,  204,
	  206,  207,  208,  209,  210,  211,  212,  213,  215,  216,  218,  219,
	  220,  221,  222,  223,  222,  223,  223,    4,  224,  238,  239,  240,
	  242,  245,  247,  263,  225,  226,  227,  228,  229,  230,  231,  232,
	  233,  234,  235,  236,  237,    4,   88,  238,  241,  243,  244,  239,
	  246,  248,  254,  249,  250,  251,  252,  253,  255,  256,  257,  258,
	  259,  260,  261,  262,  264,  265,  266,  267,  268,  269,  270,  271,
	  272,  273,  274,  275,  277,  278,  276,  274,  275,  276,  274,  277,
	  278,    5,   15,   17,   31,   34,   37,   38,   40,   43,   45,   89,
	  273,  280,  281,  292,  282,  283,  284,  287,  285,  286,  288,  289,
	  290,  291,  293,  294,    0
	};
}

private static final short _lexer_trans_targs[] = init__lexer_trans_targs_0();


private static short[] init__lexer_trans_actions_0()
{
	return new short [] {
	   43,    0,    0,   54,    3,    1,    0,   29,    1,   29,   29,   29,
	   29,   29,   29,   35,    0,    0,    0,    7,  139,   48,    0,  102,
	    9,    5,   45,  134,   45,    0,   33,  122,   33,   33,    0,   11,
	  106,    0,    0,  114,   25,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,   57,  149,  126,    0,
	  110,   23,    0,   27,  118,   27,   51,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,   57,  144,    0,   54,    0,   69,
	   33,   84,   84,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,   13,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,   13,   31,  130,   60,   57,   31,   63,   57,   63,   63,
	   63,   63,   63,   63,   66,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,   57,  144,    0,   54,    0,   81,   84,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,   21,    0,    0,   57,
	  144,    0,   54,    0,   75,   33,   84,   84,   84,   84,   84,   84,
	   84,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,   17,   17,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,   57,  144,    0,   54,    0,   78,
	   33,   84,   84,   84,   84,   84,   84,   84,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,   19,   19,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,   57,  144,    0,   54,    0,   72,   33,   84,   84,   84,
	   84,   84,   84,   84,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,   15,   15,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,   37,   37,   54,   37,   87,    0,    0,   39,    0,
	    0,   93,   90,   41,   96,   90,   96,   96,   96,   96,   96,   96,
	   99,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0
	};
}

private static final short _lexer_trans_actions[] = init__lexer_trans_actions_0();


private static short[] init__lexer_eof_actions_0()
{
	return new short [] {
	    0,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,   43,
	   43,   43,   43,   43,   43,   43,   43,   43
	};
}

private static final short _lexer_eof_actions[] = init__lexer_eof_actions_0();


static final int lexer_start = 1;
static final int lexer_first_final = 295;

static final int lexer_en_main = 1;


// line 159 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"

  public void scan(String source)  {
    String input = source + "\n%_FEATURE_END_%";
    byte[] data = null;
    try {
      data = input.getBytes("UTF-8");
    } catch(UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
    int cs, p = 0, pe = data.length;
    int eof = pe;

    int lineNumber = 1;
    int lastNewline = 0;

    int contentStart = -1;
    int currentLine = -1;
    int docstringContentTypeStart = -1;
    int docstringContentTypeEnd = -1;
    int startCol = -1;
    int nextKeywordStart = -1;
    int keywordStart = -1;
    String keyword = null;
    List<String> currentRow = null;

    
// line 489 "java/src/main/java/gherkin/lexer/Fi.java"
	{
	cs = lexer_start;
	}

// line 185 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
    
// line 496 "java/src/main/java/gherkin/lexer/Fi.java"
	{
	int _klen;
	int _trans = 0;
	int _acts;
	int _nacts;
	int _keys;
	int _goto_targ = 0;

	_goto: while (true) {
	switch ( _goto_targ ) {
	case 0:
	if ( p == pe ) {
		_goto_targ = 4;
		continue _goto;
	}
	if ( cs == 0 ) {
		_goto_targ = 5;
		continue _goto;
	}
case 1:
	_match: do {
	_keys = _lexer_key_offsets[cs];
	_trans = _lexer_index_offsets[cs];
	_klen = _lexer_single_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + _klen - 1;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + ((_upper-_lower) >> 1);
			if ( data[p] < _lexer_trans_keys[_mid] )
				_upper = _mid - 1;
			else if ( data[p] > _lexer_trans_keys[_mid] )
				_lower = _mid + 1;
			else {
				_trans += (_mid - _keys);
				break _match;
			}
		}
		_keys += _klen;
		_trans += _klen;
	}

	_klen = _lexer_range_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + (_klen<<1) - 2;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + (((_upper-_lower) >> 1) & ~1);
			if ( data[p] < _lexer_trans_keys[_mid] )
				_upper = _mid - 2;
			else if ( data[p] > _lexer_trans_keys[_mid+1] )
				_lower = _mid + 2;
			else {
				_trans += ((_mid - _keys)>>1);
				break _match;
			}
		}
		_trans += _klen;
	}
	} while (false);

	_trans = _lexer_indicies[_trans];
	cs = _lexer_trans_targs[_trans];

	if ( _lexer_trans_actions[_trans] != 0 ) {
		_acts = _lexer_trans_actions[_trans];
		_nacts = (int) _lexer_actions[_acts++];
		while ( _nacts-- > 0 )
	{
			switch ( _lexer_actions[_acts++] )
			{
	case 0:
// line 16 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      contentStart = p;
      currentLine = lineNumber;
      if(keyword != null) {
        startCol = p - lastNewline - (keyword.length() + 1);
      }
    }
	break;
	case 1:
// line 24 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      currentLine = lineNumber;
      startCol = p - lastNewline;
    }
	break;
	case 2:
// line 29 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      contentStart = p;
    }
	break;
	case 3:
// line 33 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      docstringContentTypeStart = p;
    }
	break;
	case 4:
// line 37 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      docstringContentTypeEnd = p;
    }
	break;
	case 5:
// line 41 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      String con = unindent(startCol, substring(data, contentStart, nextKeywordStart-1).replaceFirst("(\\r?\\n)?([\\t ])*\\Z", "").replaceAll("\\\\\"\\\\\"\\\\\"", "\"\"\""));
      String conType = substring(data, docstringContentTypeStart, docstringContentTypeEnd).trim();
      listener.docString(conType, con, currentLine);
    }
	break;
	case 6:
// line 47 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.feature(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 7:
// line 54 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.background(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 8:
// line 61 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.scenario(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 9:
// line 68 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.scenarioOutline(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 10:
// line 75 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      String[] nameDescription = nameAndUnindentedDescription(startCol, keywordContent(data, p, eof, nextKeywordStart, contentStart));
      listener.examples(keyword, nameDescription[0], nameDescription[1], currentLine);
      if(nextKeywordStart != -1) p = nextKeywordStart - 1;
      nextKeywordStart = -1;
    }
	break;
	case 11:
// line 82 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      listener.step(keyword, substring(data, contentStart, p).trim(), currentLine);
    }
	break;
	case 12:
// line 86 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      listener.comment(substring(data, contentStart, p).trim(), lineNumber);
      keywordStart = -1;
    }
	break;
	case 13:
// line 91 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      listener.tag(substring(data, contentStart, p).trim(), currentLine);
      keywordStart = -1;
    }
	break;
	case 14:
// line 96 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      lineNumber++;
    }
	break;
	case 15:
// line 100 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      lastNewline = p + 1;
    }
	break;
	case 16:
// line 104 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      if(keywordStart == -1) keywordStart = p;
    }
	break;
	case 17:
// line 108 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      keyword = substring(data, keywordStart, p).replaceFirst(":$","");
      keywordStart = -1;
    }
	break;
	case 18:
// line 113 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      nextKeywordStart = p;
    }
	break;
	case 19:
// line 117 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      p = p - 1;
      currentRow = new ArrayList<String>();
      currentLine = lineNumber;
    }
	break;
	case 20:
// line 123 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      contentStart = p;
    }
	break;
	case 21:
// line 127 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      String con = substring(data, contentStart, p).trim();
      currentRow.add(con
        .replaceAll("\\\\\\|", "|")
        .replaceAll("\\\\n", "\n")
        .replaceAll("\\\\\\\\", "\\\\")
      );
    }
	break;
	case 22:
// line 136 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      listener.row(currentRow, currentLine);
    }
	break;
	case 23:
// line 140 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      if(cs < lexer_first_final) {
        String content = currentLineContent(data, lastNewline);
        throw new LexingError("Lexing error on line " + lineNumber + ": '" + content + "'. See http://wiki.github.com/cucumber/gherkin/lexingerror for more information.");
      } else {
        listener.eof();
      }
    }
	break;
// line 757 "java/src/main/java/gherkin/lexer/Fi.java"
			}
		}
	}

case 2:
	if ( cs == 0 ) {
		_goto_targ = 5;
		continue _goto;
	}
	if ( ++p != pe ) {
		_goto_targ = 1;
		continue _goto;
	}
case 4:
	if ( p == eof )
	{
	int __acts = _lexer_eof_actions[cs];
	int __nacts = (int) _lexer_actions[__acts++];
	while ( __nacts-- > 0 ) {
		switch ( _lexer_actions[__acts++] ) {
	case 23:
// line 140 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
	{
      if(cs < lexer_first_final) {
        String content = currentLineContent(data, lastNewline);
        throw new LexingError("Lexing error on line " + lineNumber + ": '" + content + "'. See http://wiki.github.com/cucumber/gherkin/lexingerror for more information.");
      } else {
        listener.eof();
      }
    }
	break;
// line 789 "java/src/main/java/gherkin/lexer/Fi.java"
		}
	}
	}

case 5:
	}
	break; }
	}

// line 186 "/Users/ahellesoy/github/gherkin/tasks/../ragel/i18n/fi.java.rl"
  }

  private String keywordContent(byte[] data, int p, int eof, int nextKeywordStart, int contentStart) {
    int endPoint = (nextKeywordStart == -1 || (p == eof)) ? p : nextKeywordStart;
    return substring(data, contentStart, endPoint);
  }

  private String[] nameAndUnindentedDescription(int startCol, String text) {
    String[] lines = text.split("\n");
    String name = lines.length > 0 ? lines[0].trim() : "";
    StringBuffer description = new StringBuffer();
    for(int i = 1; i < lines.length; i++) {
      description.append(lines[i]);
      description.append("\n");
    }
    return new String[]{name, unindent(startCol+2, description.toString()).replaceAll("\\s+$", "")};
  }

  private String unindent(int startCol, String text) {
    return Pattern.compile("^[\t ]{0," + startCol + "}", Pattern.MULTILINE).matcher(text).replaceAll("");
  }

  private String currentLineContent(byte[] data, int lastNewline) {
    return substring(data, lastNewline, data.length).trim();
  }

  private String substring(byte[] data, int start, int end) {
    try {
      return new String(data, start, end-start, "utf-8");
    } catch(java.io.UnsupportedEncodingException e) {
      throw new RuntimeException("Internal error", e);
    }
  }
}
