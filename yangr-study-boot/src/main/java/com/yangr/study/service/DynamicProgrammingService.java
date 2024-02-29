package com.yangr.study.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DynamicProgrammingService {

    public static void main(String[] args) {
        int [][] map  = {
                {1,2,3,4,5},
                {1,3,4,5,6},
                {1,4,5,6,7},
                {1,5,6,7,8},
                {1,6,7,8,9}
        };
        db(map);
    }

    public static int [][] db(int [][] map) {
        int [][] dp = new int[map.length][map[0].length];
        dp[0][0] = map[0][0];
        for (int i = 1; i < map.length; i++) {
            dp[i][0] = map[i][0] + dp[i-1][0];
        }
        for (int j = 1; j < map[0].length; j++) {
            dp[0][j] = map[0][j] + dp[0][j-1];
        }


        for (int i = 1; i < map.length; i++) {
            int[] ints = map[i];
            for (int j = 1; j < ints.length; j++) {
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + map[i][j];
            }
        }


        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        return dp;
    }







}
