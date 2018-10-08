package com.qin.myutils.retrofit.test;

import java.util.List;

/**
 * Create by qindl
 * on 2018/10/8
 */
public class Menu {


    /**
     * resultcode : 200
     * reason : Success
     * result : [{"parentId":"10001","name":"菜式菜品","list":[{"id":"1","name":"家常菜","parentId":"10001"},{"id":"8","name":"汤","parentId":"10001"},{"id":"9","name":"自制调味料","parentId":"10001"}]},{"parentId":"10002","name":"菜系","list":[{"id":"10","name":"川菜","parentId":"10002"},{"id":"11","name":"粤菜","parentId":"10002"},{"id":"12","name":"湘菜","parentId":"10002"}]}]
     */

    private String resultcode;
    private String reason;
    private List<ResultBean> result;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * parentId : 10001
         * name : 菜式菜品
         * list : [{"id":"1","name":"家常菜","parentId":"10001"},{"id":"8","name":"汤","parentId":"10001"},{"id":"9","name":"自制调味料","parentId":"10001"}]
         */

        private String parentId;
        private String name;
        private List<ListBean> list;

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * name : 家常菜
             * parentId : 10001
             */

            private String id;
            private String name;
            private String parentId;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }
        }
    }
}
