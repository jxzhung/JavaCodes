package core.http.httpclient;

import java.util.List;

/**
 * Created by Jzhung on 2016/12/13.
 */
public class PhoneResponse {

    /**
     * code : 0
     * errorDescription : null
     * dataObject : {"pageend":5,"querystatus":1,"maxPage":44,"listphones":[{"phoneNumber":"17316087195","prepayMent":0,"status":2,"minAmount":0,"salesProdId":"000000003D655A34435B3DB7E053AA1410AC8503","level":1,"province":"北京","provinceCode":"609001","city":"市辖区","cityCode":"8110100"},{"phoneNumber":"17316087196","prepayMent":0,"status":2,"minAmount":0,"salesProdId":"000000003D655A34435B3DB7E053AA1410AC8503","level":1,"province":"北京","provinceCode":"609001","city":"市辖区","cityCode":"8110100"},{"phoneNumber":"17316087197","prepayMent":0,"status":2,"minAmount":0,"salesProdId":"000000003D655A34435B3DB7E053AA1410AC8503","level":1,"province":"北京","provinceCode":"609001","city":"市辖区","cityCode":"8110100"},{"phoneNumber":"17316087201","prepayMent":0,"status":2,"minAmount":0,"salesProdId":"000000003D655A34435B3DB7E053AA1410AC8503","level":1,"province":"北京","provinceCode":"609001","city":"市辖区","cityCode":"8110100"},{"phoneNumber":"17316087202","prepayMent":0,"status":2,"minAmount":0,"salesProdId":"000000003D655A34435B3DB7E053AA1410AC8503","level":1,"province":"北京","provinceCode":"609001","city":"市辖区","cityCode":"8110100"},{"phoneNumber":"17316087205","prepayMent":0,"status":2,"minAmount":0,"salesProdId":"000000003D655A34435B3DB7E053AA1410AC8503","level":1,"province":"北京","provinceCode":"609001","city":"市辖区","cityCode":"8110100"},{"phoneNumber":"17316087207","prepayMent":0,"status":2,"minAmount":0,"salesProdId":"000000003D655A34435B3DB7E053AA1410AC8503","level":1,"province":"北京","provinceCode":"609001","city":"市辖区","cityCode":"8110100"},{"phoneNumber":"17316087209","prepayMent":0,"status":2,"minAmount":0,"salesProdId":"000000003D655A34435B3DB7E053AA1410AC8503","level":1,"province":"北京","provinceCode":"609001","city":"市辖区","cityCode":"8110100"}],"totalCount":351,"pageindex":6}
     */

    private String code;
    private Object errorDescription;
    private DataObjectEntity dataObject;


    public void setCode(String code) {
        this.code = code;
    }

    public void setErrorDescription(Object errorDescription) {
        this.errorDescription = errorDescription;
    }

    public void setDataObject(DataObjectEntity dataObject) {
        this.dataObject = dataObject;
    }

    public String getCode() {
        return code;
    }

    public Object getErrorDescription() {
        return errorDescription;
    }

    public DataObjectEntity getDataObject() {
        return dataObject;
    }


    public static class DataObjectEntity {
        /**
         * pageend : 5
         * querystatus : 1
         * maxPage : 44
         * listphones : [{"phoneNumber":"17316087195","prepayMent":0,"status":2,"minAmount":0,"salesProdId":"000000003D655A34435B3DB7E053AA1410AC8503","level":1,"province":"北京","provinceCode":"609001","city":"市辖区","cityCode":"8110100"},{"phoneNumber":"17316087196","prepayMent":0,"status":2,"minAmount":0,"salesProdId":"000000003D655A34435B3DB7E053AA1410AC8503","level":1,"province":"北京","provinceCode":"609001","city":"市辖区","cityCode":"8110100"},{"phoneNumber":"17316087197","prepayMent":0,"status":2,"minAmount":0,"salesProdId":"000000003D655A34435B3DB7E053AA1410AC8503","level":1,"province":"北京","provinceCode":"609001","city":"市辖区","cityCode":"8110100"},{"phoneNumber":"17316087201","prepayMent":0,"status":2,"minAmount":0,"salesProdId":"000000003D655A34435B3DB7E053AA1410AC8503","level":1,"province":"北京","provinceCode":"609001","city":"市辖区","cityCode":"8110100"},{"phoneNumber":"17316087202","prepayMent":0,"status":2,"minAmount":0,"salesProdId":"000000003D655A34435B3DB7E053AA1410AC8503","level":1,"province":"北京","provinceCode":"609001","city":"市辖区","cityCode":"8110100"},{"phoneNumber":"17316087205","prepayMent":0,"status":2,"minAmount":0,"salesProdId":"000000003D655A34435B3DB7E053AA1410AC8503","level":1,"province":"北京","provinceCode":"609001","city":"市辖区","cityCode":"8110100"},{"phoneNumber":"17316087207","prepayMent":0,"status":2,"minAmount":0,"salesProdId":"000000003D655A34435B3DB7E053AA1410AC8503","level":1,"province":"北京","provinceCode":"609001","city":"市辖区","cityCode":"8110100"},{"phoneNumber":"17316087209","prepayMent":0,"status":2,"minAmount":0,"salesProdId":"000000003D655A34435B3DB7E053AA1410AC8503","level":1,"province":"北京","provinceCode":"609001","city":"市辖区","cityCode":"8110100"}]
         * totalCount : 351
         * pageindex : 6
         */

        private int pageend;
        private int querystatus;
        private int maxPage;
        private int totalCount;
        private int pageindex;
        private List<ListphonesEntity> listphones;

        public void setPageend(int pageend) {
            this.pageend = pageend;
        }

        public void setQuerystatus(int querystatus) {
            this.querystatus = querystatus;
        }

        public void setMaxPage(int maxPage) {
            this.maxPage = maxPage;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public void setPageindex(int pageindex) {
            this.pageindex = pageindex;
        }

        public void setListphones(List<ListphonesEntity> listphones) {
            this.listphones = listphones;
        }

        public int getPageend() {
            return pageend;
        }

        public int getQuerystatus() {
            return querystatus;
        }

        public int getMaxPage() {
            return maxPage;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public int getPageindex() {
            return pageindex;
        }

        public List<ListphonesEntity> getListphones() {
            return listphones;
        }

        public static class ListphonesEntity {
            /**
             * phoneNumber : 17316087195
             * prepayMent : 0.0
             * status : 2
             * minAmount : 0.0
             * salesProdId : 000000003D655A34435B3DB7E053AA1410AC8503
             * level : 1
             * province : 北京
             * provinceCode : 609001
             * city : 市辖区
             * cityCode : 8110100
             */

            private String phoneNumber;
            private double prepayMent;
            private int status;
            private double minAmount;
            private String salesProdId;
            private int level;
            private String province;
            private String provinceCode;
            private String city;
            private String cityCode;

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public void setPrepayMent(double prepayMent) {
                this.prepayMent = prepayMent;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public void setMinAmount(double minAmount) {
                this.minAmount = minAmount;
            }

            public void setSalesProdId(String salesProdId) {
                this.salesProdId = salesProdId;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public void setProvinceCode(String provinceCode) {
                this.provinceCode = provinceCode;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public void setCityCode(String cityCode) {
                this.cityCode = cityCode;
            }

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public double getPrepayMent() {
                return prepayMent;
            }

            public int getStatus() {
                return status;
            }

            public double getMinAmount() {
                return minAmount;
            }

            public String getSalesProdId() {
                return salesProdId;
            }

            public int getLevel() {
                return level;
            }

            public String getProvince() {
                return province;
            }

            public String getProvinceCode() {
                return provinceCode;
            }

            public String getCity() {
                return city;
            }

            public String getCityCode() {
                return cityCode;
            }
        }
    }
}
