##  将开源库发布到jcenter

1. 先注册一个bintray账号.网址是`https://bintray.com`
2. 然后在我的首页,点击`Add New Repository`创建一个新的仓库.仓库类型选择`Maven`,开源许可随便选,可以是`Apache-2.0`
3. 回到我们编写的库中,在项目的build.gradle中引入`bintray-release`
```groovy
buildscript {
    dependencies {
        classpath 'com.novoda:bintray-release:0.9.1'
    }
}
```
4. 然后在开源库Library的build.gradle中填写如下配置
```groovy
apply plugin: 'com.novoda.bintray-release'
publish {
    userOrg = 'xfhy'//bintray.com用户名
    groupId = 'com.permissionx.xfhy'//jcenter上的路径
    artifactId = 'permissionx'//项目名称
    repoName = "permissionx"
    publishVersion = '1.0.0'//版本号
    desc = 'Make Android runtime permission request easy'//描述，不重要
    website = 'https://github.com/xfhy/PermissionX'//网站，不重要
}

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.novoda:bintray-release:0.9.1'
    }
}
```
5. 填写完成之后开始上传,在命令行输入`//上传命令:  ./gradlew clean build bintrayUpload -PbintrayUser=xfhy -PbintrayKey=xxxxx -PdryRun=false`   其中PbintrayKey是Bintray的API key.
6. 命令执行完成之后,到Bintray中找到之前创建的仓库,点进去详情,点击`Add to Jcenter`,发送申请.
7. 几小时就能审核通过,然后就可以使用这个开源库了.
8. 使用方式`implementation 'com.permissionx.xfhy:permissionx:1.0.0'`