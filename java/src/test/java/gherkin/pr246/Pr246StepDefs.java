package gherkin.pr246;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.List;

/**
 * Defines the steps for PullRequest 246.
 * @since 3/25/13 2:32 PM
 */
public class Pr246StepDefs
{
    /**
     * The tax amount.
     */
    private BigDecimal taxAmount = null;

    /**
     * The receipt.
     */
    private String receipt = null;

    /**
     * Step to annotate the tax amount.
     * @param taxAmount such amount.
     */
    @Given("a user has won a prize and (.*) euros correspond to taxes")
    public void aTaxAmount(final String taxAmount)
    {
        this.taxAmount = new BigDecimal(taxAmount);
    }

    /**
     * Step to create a tax receipt.
     */
    @When("I request the creation of a tax receipt")
    public void createTaxReceipt()
    {
        receipt = "receipt for " + this.taxAmount;
    }

    @Then("the tax receipt is created correctly")
    public void taxReceiptCreatedCorrectly()
    {
        Assert.assertNotNull("The tax receipt was not created", receipt);
    }
}
