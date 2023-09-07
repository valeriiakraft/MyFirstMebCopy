@FindBy(xpath = "//span[contains(text(),'0 RUB')][1]")
    private WebElement minimumSum;
    @FindBy(xpath = "//span[contains(text(),'0 RUB')][2]")
    private WebElement maximumSum;
    @FindBy(xpath = "//div[@class='MuiBox-root css-rsohv2']/child::span[contains(text(),'%')][1]")
    private WebElement minimumRate;
    @FindBy(xpath = "//div[@class='MuiBox-root css-rsohv2']/child::span[contains(text(),'%')][2]")
    private WebElement maximumRate;

    @FindBy(xpath = "//div[@class='MuiBox-root css-rsohv2']/child::span[contains(text(),'месяц')][1]")
    private WebElement minimumTime;
    @FindBy(xpath = "//div[@class='MuiBox-root css-rsohv2']/child::span[contains(text(),'месяц')][2]")
    private WebElement maximumTime;



    @FindBy(xpath = "//div/p[text()='Тип депозита']/following::div/span[contains(text(),'')]")
    private WebElement DepositType;

    @FindBy(xpath = "//div/p[text()='Валюта']/following::div[1]/span[contains(text(),'')]")
    private WebElement depositValue;

    @FindBy(xpath = "//div/p[text()='Возможность досрочного снятия']/following::div[1]/span[contains(text(),'')]")
    private WebElement possibilityOfEarlyWithdrawal;

    @FindBy(xpath = "//div/p[text()='Ставка при досрочном снятии']/following::div[1]/span[contains(text(),'')]")
    private WebElement earlyWithdrawalInterestRate;

    @FindBy(xpath = "//div/p[text()='Пополнение']/following::div[1]/span[contains(text(),'')]")
    private WebElement replenishment;

    @FindBy(xpath = "//div/p[text()='Капитализация']/following::div[1]/span[contains(text(),'')]")
    private WebElement capitalization;

