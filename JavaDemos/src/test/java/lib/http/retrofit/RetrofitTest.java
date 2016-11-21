package lib.http.retrofit;

import org.junit.Test;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.io.IOException;

/**
 * Retrofit测试
 * 官网 http://square.github.io/retrofit/
 */
public class RetrofitTest {
    @Test
    public void testGetUsers() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://int.dpool.sina.com.cn/")
                .addConverterFactory(GsonConverterFactory.create()) //需要converter-gson库
                .build();

        BaiduService service = retrofit.create(BaiduService.class);
        Call<IPResult> searchCall = service.getIp("117.25.139.35");
        IPResult body = searchCall.execute().body();
        System.out.println(body);
    }

    public interface BaiduService {
        @GET("iplookup/iplookup.php?format=json")
        Call<IPResult> getIp(@Query("ip") String ip);
    }

    class IPResult {

        /**
         * ret : 1
         * start : -1
         * end : -1
         * country : 中国
         * province : 北京
         * city : 北京
         * district :
         * isp :
         * type :
         * desc :
         */

        private int ret;
        private int start;
        private int end;
        private String country;
        private String province;
        private String city;
        private String district;
        private String isp;
        private String type;
        private String desc;

        public int getRet() {
            return ret;
        }

        public void setRet(int ret) {
            this.ret = ret;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getIsp() {
            return isp;
        }

        public void setIsp(String isp) {
            this.isp = isp;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "WeatherResult{" +
                    "ret=" + ret +
                    ", start=" + start +
                    ", end=" + end +
                    ", country='" + country + '\'' +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", district='" + district + '\'' +
                    ", isp='" + isp + '\'' +
                    ", type='" + type + '\'' +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }

}
