package com.example.samuelleung.testingapplication;/*
* COPYRIGHT. HSBC HOLDINGS PLC 2016. ALL RIGHTS RESERVED.
*
* This software is only to be used for the purpose for which it has been
* provided. No part of it is to be reproduced, disassembled, transmitted,
* stored in a retrieval system nor translated in any human or computer
* language in any way or for any other purposes whatsoever without the
* prior written consent of HSBC Holdings plc.
*/

import java.util.Random;

public class RandomHelper {

    public static int getRandomNumber(int min, int max) {
        Random r = new Random();
        int i1 = r.nextInt(max - min + 1) + min;
        return i1;
    }

}
