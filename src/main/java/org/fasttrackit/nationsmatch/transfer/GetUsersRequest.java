package org.fasttrackit.nationsmatch.transfer;

public class GetUsersRequest {

    private String partialFirstName;
    private String partialLastName;
    private Integer minAge;
    private Integer maxAge;
    private String sameNationality;

    public String getPartialFirstName() {
        return partialFirstName;
    }

    public void setPartialFirstName(String partialFirstName) {
        this.partialFirstName = partialFirstName;
    }

    public String getPartialLastName() {
        return partialLastName;
    }

    public void setPartialLastName(String partialLastName) {
        this.partialLastName = partialLastName;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public String getSameNationality() {
        return sameNationality;
    }

    public void setSameNationality(String sameNationality) {
        this.sameNationality = sameNationality;
    }

    @Override
    public String toString() {
        return "GetUsersRequest{" +
                "partialFirstName='" + partialFirstName + '\'' +
                ", partialLastName='" + partialLastName + '\'' +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", sameNationality='" + sameNationality + '\'' +
                '}';
    }
}
