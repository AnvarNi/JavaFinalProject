package com.company;

public class Country {

    public String Country;
    public String Region;
    public int HappinessRank;
    public float HappinessScore;
    public float StandardError;
    public float Economy;
    public float Family;
    public float Health;
    public float Freedom;
    public float Trust;
    public float Generosity;
    public float DystopiaResidual;

    public Country() {}

    public Country(String Country, String Region, int HappinessRank, float HappinessScore, float StandardError,
                   float Economy, float Family, float Health, float Freedom, float Trust, float Generosity, float DystopiaResidual) {
        super();
        this.Country = Country;
        this.Region = Region;
        this.HappinessRank = HappinessRank;
        this.HappinessScore = HappinessScore;
        this.StandardError = StandardError;
        this.Economy = Economy;
        this.Family = Family;
        this.Health = Health;
        this.Freedom = Freedom;
        this.Trust = Trust;
        this.Generosity = Generosity;
        this.DystopiaResidual = DystopiaResidual;
    }

    @Override
    public String toString() {
        return "Country [Country=" + Country + ", Region=" + Region + ", Happiness Rank=" + HappinessRank + ", Happiness Score=" + HappinessScore +
                ", Standard Error=" + StandardError + ", Economy=" + Economy + ", Family=" + Family + ", Health=" + Health + ", Freedom=" + Freedom +
                ", Trust=" + Trust + ", Generosity=" + Generosity + ", Dystopia Residual" + DystopiaResidual + "]";
    }

    public String getCountry() { return Country; }
    public void setCountry() {
        this.Country = Country;
    }

    public String getRegion() {
        return Region;
    }
    public void setRegion() {
        this.Region = Region;
    }

    public int getHappinessRank() {
        return HappinessRank;
    }
    public void setHappinessRank() { this.HappinessRank = HappinessRank; }

    public float getHappinessScore() {
        return HappinessScore;
    }
    public void setHappinessScore() {
        this.HappinessScore = HappinessScore;
    }

    public float getStandardError() {
        return StandardError;
    }
    public void setStandardError() {
        this.StandardError = StandardError;
    }

    public float getEconomy() {
        return Economy;
    }
    public void setEconomy() {
        this.Economy = Economy;
    }

    public float getFamily() {
        return Family;
    }
    public void setFamily() {
        this.Family = Family;
    }

    public float getHealth() {
        return Health;
    }
    public void setHealth() { this.Health = Health; }

    public float getFreedom() { return Freedom; }
    public void setFreedom() {
        this.Freedom = Freedom;
    }

    public float getTrust() {
        return Trust;
    }
    public void setTrust() {
        this.Trust = Trust;
    }

    public float getGenerosity() {
        return Generosity;
    }
    public void setGenerosity() {
        this.Generosity = Generosity;
    }

    public float getDystopiaResidual() {
        return DystopiaResidual;
    }
    public void setDystopiaResidual() {
        this.DystopiaResidual = DystopiaResidual;
    }
}
