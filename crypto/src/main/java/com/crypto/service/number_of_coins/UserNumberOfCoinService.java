package com.crypto.service.number_of_coins;

import com.crypto.request.number_of_coins.UserAddNumberOfCoins;

/**
 * Created by Semih, 25.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
public abstract class UserNumberOfCoinService {

    public abstract Boolean addUserNewNumberOfCoins(UserAddNumberOfCoins request);
}
