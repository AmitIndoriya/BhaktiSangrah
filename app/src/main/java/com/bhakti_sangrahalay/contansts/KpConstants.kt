package com.bhakti_sangrahalay.contansts

object KpConstants {
    val y1 = intArrayOf(7, 20, 6, 10, 7, 18, 16, 19, 17)
    const val SUN = 0
    const val MOON = 1
    const val MARS = 2
    const val MERCURY = 3
    const val JUPITER = 4
    const val VENUS = 5
    const val SAT = 6
    const val RAHU = 7
    const val KETU = 8
    val PLANET_NAKSHTRA_LORD = intArrayOf(
        KETU, VENUS, SUN, MOON, MARS, RAHU, JUPITER, SAT, MERCURY,
        KETU, VENUS, SUN, MOON, MARS, RAHU, JUPITER, SAT, MERCURY,
        KETU, VENUS, SUN, MOON, MARS, RAHU, JUPITER, SAT, MERCURY
    )
    val PLANET_RASHI = arrayOf(
        intArrayOf(5, 0),
        intArrayOf(4, 0),
        intArrayOf(1, 8),
        intArrayOf(3, 6),
        intArrayOf(9, 12),
        intArrayOf(2, 7),
        intArrayOf(10, 11),
        intArrayOf(0, 0),
        intArrayOf(0, 0)
    )
    var PLANET_INDEX = intArrayOf(SUN, MOON, MARS, MERCURY, JUPITER, VENUS, SAT, RAHU, KETU)

    var RASHI_TO_PLANET = intArrayOf(
        -1, MARS, VENUS, MERCURY, MOON, SUN, MERCURY, VENUS, MARS, JUPITER, SAT, SAT, JUPITER
    )

    /*public static final  int URANUS=9;
	public static final  int NEPTUNE=10;
	public static final  int PLUTO=11;*/
    const val CUSP1 = 0
    const val CUSP2 = 1
    const val CUSP3 = 2
    const val CUSP4 = 3
    const val CUSP5 = 4
    const val CUSP6 = 5
    const val CUSP7 = 6
    const val CUSP8 = 7
    const val CUSP9 = 8
    const val CUSP10 = 9
    const val CUSP11 = 10
    const val CUSP12 = 11
}