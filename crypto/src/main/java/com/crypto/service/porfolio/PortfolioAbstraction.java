package com.crypto.service.porfolio;

import com.crypto.model.portfolio.UserPortfolio;
import com.crypto.request.porfolio.UserPortfolioRequest;

/**
 * @author semih on Haziran, 2022, 6.06.2022, 22:27:43
 */
public abstract class PortfolioAbstraction {

	public abstract UserPortfolio showUserPortfolio(UserPortfolioRequest request);
}
