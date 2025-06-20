const base = {
    get() {
        return {
            url : "http://localhost:8080/springbootzpl96/",
            name: "springbootzpl96",
            // 退出到首页链接
            indexUrl: ''
        };
    },
    getProjectName(){
        return {
            projectName: "高校毕业与学位资格审核系统"
        } 
    }
}
export default base
