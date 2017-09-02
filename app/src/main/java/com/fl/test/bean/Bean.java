package com.fl.test.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/4/9.
 */
public class Bean {
    /**
     * code : 200
     * message : 会议申请
     * data : {"meetingType":[{"id":"1","addtime":"2015-10-09 17:03:46","delsign":"0","type":"3","huiyi_type":"公益讲座 ","admin_id":["8"],"lecturer_id":["1"],"duration":"1"},{"id":"2","addtime":"2015-10-09 17:03:56","delsign":"0","type":"2","huiyi_type":"基础培训","admin_id":["9","10"],"lecturer_id":["1","2"],"duration":"1"},{"id":"3","addtime":"2015-10-09 17:04:05","delsign":"0","type":"3","huiyi_type":"领导人训","admin_id":["11","12"],"lecturer_id":["2","3"],"duration":"2"},{"id":"4","addtime":"2015-10-09 17:04:14","delsign":"0","type":"1","huiyi_type":"智慧培训","admin_id":["13"],"lecturer_id":["4"],"duration":"3"},{"id":"5","addtime":"2015-10-09 17:04:24","delsign":"0","type":"2","huiyi_type":"推进会","admin_id":["12"],"lecturer_id":["1","2"],"duration":"2"},{"id":"6","addtime":"2015-10-09 17:04:24","delsign":"0","type":"3","huiyi_type":"体系大会","admin_id":["14"],"lecturer_id":["1","2","3","4","5"],"duration":"3"}],"addr":[{"id":"2","diqu":"东北","status":"0","addtime":"2015-10-09 17:01:43"},{"id":"3","diqu":"华北","status":"0","addtime":"2015-10-09 17:01:59"},{"id":"4","diqu":"京津冀","status":"0","addtime":"2015-10-09 17:02:14"},{"id":"5","diqu":"华东","status":"0","addtime":"2015-10-09 17:02:25"},{"id":"6","diqu":"华南","status":"0","addtime":"2015-10-09 17:02:35"},{"id":"7","diqu":"华中","status":"0","addtime":"2015-10-09 17:02:58"},{"id":"8","diqu":"西北-西安","status":"0","addtime":"2015-10-09 17:03:10"}],"lecturerInfo":[{"js_id":"7","js_name":"Z","xingming":"张三","dianhua":"1522291585","mima":"670b14728ad9902aecba32e22fa4f6bd","jibie":"","status":"0","js_class":null,"exp":"36.00","type":"1","promote_id":"1","scheme_id":"1","total":"6","delsign":"0","js_img":"./uploads/201603/18//56ebba16d6923.jpg","class":null},{"js_id":"8","js_name":"zlw","xingming":"李四","dianhua":"15222913585","mima":"670b14728ad9902aecba32e22fa4f6bd","jibie":"","status":"0","js_class":null,"exp":"302.00","type":"0","promote_id":"2","scheme_id":"1","total":"23","delsign":"0","js_img":"./uploads/201603/18//56ebba3a6ae9d.jpg","class":null},{"js_id":"9","js_name":"W","xingming":"王五","dianhua":"15222913585","mima":"670b14728ad9902aecba32e22fa4f6bd","jibie":"","status":"0","js_class":null,"exp":"88.00","type":"0","promote_id":"1","scheme_id":"1","total":"11","delsign":"0","js_img":"./uploads/201603/18//56ebba5826f81.jpg","class":null},{"js_id":"10","js_name":"maoliu","xingming":"毛六","dianhua":"15555555555","mima":"e10adc3949ba59abbe56e057f20f883e","jibie":"","status":"0","js_class":null,"exp":"66.00","type":"0","promote_id":"2","scheme_id":"5","total":"6","delsign":"0","js_img":null,"class":null}],"province":[{"region_id":"2","region_name":"北京"},{"region_id":"3","region_name":"安徽"},{"region_id":"4","region_name":"福建"},{"region_id":"5","region_name":"甘肃"},{"region_id":"6","region_name":"广东"},{"region_id":"7","region_name":"广西"},{"region_id":"8","region_name":"贵州"},{"region_id":"9","region_name":"海南"},{"region_id":"10","region_name":"河北"},{"region_id":"11","region_name":"河南"},{"region_id":"12","region_name":"黑龙江"},{"region_id":"13","region_name":"湖北"},{"region_id":"14","region_name":"湖南"},{"region_id":"15","region_name":"吉林"},{"region_id":"16","region_name":"江苏"},{"region_id":"17","region_name":"江西"},{"region_id":"18","region_name":"辽宁"},{"region_id":"19","region_name":"内蒙古"},{"region_id":"20","region_name":"宁夏"},{"region_id":"21","region_name":"青海"},{"region_id":"22","region_name":"山东"},{"region_id":"23","region_name":"山西"},{"region_id":"24","region_name":"陕西"},{"region_id":"25","region_name":"上海"},{"region_id":"26","region_name":"四川"},{"region_id":"27","region_name":"天津"},{"region_id":"28","region_name":"西藏"},{"region_id":"29","region_name":"新疆"},{"region_id":"30","region_name":"云南"},{"region_id":"31","region_name":"浙江"},{"region_id":"32","region_name":"重庆"},{"region_id":"33","region_name":"香港"},{"region_id":"34","region_name":"澳门"},{"region_id":"35","region_name":"台湾"}]}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * addtime : 2015-10-09 17:03:46
         * delsign : 0
         * type : 3
         * huiyi_type : 公益讲座
         * admin_id : ["8"]
         * lecturer_id : ["1"]
         * duration : 1
         */

        private List<MeetingTypeBean> meetingType;
        /**
         * id : 2
         * diqu : 东北
         * status : 0
         * addtime : 2015-10-09 17:01:43
         */

        private List<AddrBean> addr;
        /**
         * js_id : 7
         * js_name : Z
         * xingming : 张三
         * dianhua : 1522291585
         * mima : 670b14728ad9902aecba32e22fa4f6bd
         * jibie :
         * status : 0
         * js_class : null
         * exp : 36.00
         * type : 1
         * promote_id : 1
         * scheme_id : 1
         * total : 6
         * delsign : 0
         * js_img : ./uploads/201603/18//56ebba16d6923.jpg
         * class : null
         */

        private List<LecturerInfoBean> lecturerInfo;
        /**
         * region_id : 2
         * region_name : 北京
         */

        private List<ProvinceBean> province;

        public List<MeetingTypeBean> getMeetingType() {
            return meetingType;
        }

        public void setMeetingType(List<MeetingTypeBean> meetingType) {
            this.meetingType = meetingType;
        }

        public List<AddrBean> getAddr() {
            return addr;
        }

        public void setAddr(List<AddrBean> addr) {
            this.addr = addr;
        }

        public List<LecturerInfoBean> getLecturerInfo() {
            return lecturerInfo;
        }

        public void setLecturerInfo(List<LecturerInfoBean> lecturerInfo) {
            this.lecturerInfo = lecturerInfo;
        }

        public List<ProvinceBean> getProvince() {
            return province;
        }

        public void setProvince(List<ProvinceBean> province) {
            this.province = province;
        }

        public static class MeetingTypeBean {
            private String id;
            private String addtime;
            private String delsign;
            private String type;
            private String huiyi_type;
            private String duration;
            private List<String> admin_id;
            private List<String> lecturer_id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getDelsign() {
                return delsign;
            }

            public void setDelsign(String delsign) {
                this.delsign = delsign;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getHuiyi_type() {
                return huiyi_type;
            }

            public void setHuiyi_type(String huiyi_type) {
                this.huiyi_type = huiyi_type;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public List<String> getAdmin_id() {
                return admin_id;
            }

            public void setAdmin_id(List<String> admin_id) {
                this.admin_id = admin_id;
            }

            public List<String> getLecturer_id() {
                return lecturer_id;
            }

            public void setLecturer_id(List<String> lecturer_id) {
                this.lecturer_id = lecturer_id;
            }
        }

        public static class AddrBean {
            private String id;
            private String diqu;
            private String status;
            private String addtime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDiqu() {
                return diqu;
            }

            public void setDiqu(String diqu) {
                this.diqu = diqu;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }
        }

        public static class LecturerInfoBean {
            private String js_id;
            private String js_name;
            private String xingming;
            private String dianhua;
            private String mima;
            private String jibie;
            private String status;
            private Object js_class;
            private String exp;
            private String type;
            private String promote_id;
            private String scheme_id;
            private String total;
            private String delsign;
            private String js_img;
            @SerializedName("class")
            private Object classX;

            public String getJs_id() {
                return js_id;
            }

            public void setJs_id(String js_id) {
                this.js_id = js_id;
            }

            public String getJs_name() {
                return js_name;
            }

            public void setJs_name(String js_name) {
                this.js_name = js_name;
            }

            public String getXingming() {
                return xingming;
            }

            public void setXingming(String xingming) {
                this.xingming = xingming;
            }

            public String getDianhua() {
                return dianhua;
            }

            public void setDianhua(String dianhua) {
                this.dianhua = dianhua;
            }

            public String getMima() {
                return mima;
            }

            public void setMima(String mima) {
                this.mima = mima;
            }

            public String getJibie() {
                return jibie;
            }

            public void setJibie(String jibie) {
                this.jibie = jibie;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getJs_class() {
                return js_class;
            }

            public void setJs_class(Object js_class) {
                this.js_class = js_class;
            }

            public String getExp() {
                return exp;
            }

            public void setExp(String exp) {
                this.exp = exp;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPromote_id() {
                return promote_id;
            }

            public void setPromote_id(String promote_id) {
                this.promote_id = promote_id;
            }

            public String getScheme_id() {
                return scheme_id;
            }

            public void setScheme_id(String scheme_id) {
                this.scheme_id = scheme_id;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getDelsign() {
                return delsign;
            }

            public void setDelsign(String delsign) {
                this.delsign = delsign;
            }

            public String getJs_img() {
                return js_img;
            }

            public void setJs_img(String js_img) {
                this.js_img = js_img;
            }

            public Object getClassX() {
                return classX;
            }

            public void setClassX(Object classX) {
                this.classX = classX;
            }
        }

        public static class ProvinceBean {
            private String region_id;
            private String region_name;

            public String getRegion_id() {
                return region_id;
            }

            public void setRegion_id(String region_id) {
                this.region_id = region_id;
            }

            public String getRegion_name() {
                return region_name;
            }

            public void setRegion_name(String region_name) {
                this.region_name = region_name;
            }
        }
    }
}
