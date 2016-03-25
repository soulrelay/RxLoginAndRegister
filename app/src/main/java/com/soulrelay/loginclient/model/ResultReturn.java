package com.soulrelay.loginclient.model;

/**
 * Created by sushuai on 2016/3/25.
 */
public class ResultReturn {

    /**
     * success : true
     * msg : Login success
     * result : {"name":"ss","email":"123@qq.com","contact":"123"}
     */

    private boolean success;
    private String msg;
    /**
     * name : ss
     * email : 123@qq.com
     * contact : 123
     */

    private ResultBean result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private String name;
        private String email;
        private String contact;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }
    }
}
