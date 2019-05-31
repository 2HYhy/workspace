### 基本命令
> 设置用户名、邮箱：  
>- git config --global user.name "Your Name"  
>- git config --global user.email "Your email"    

> 查看当前git用户名邮箱
>- git config user.name 
>- cat ~/.gitconfig

> 初始化一个目录为本地仓库：  
>- git init  

> 提交文件： 
>- git add FileName 
>- git add .  //批量添加  
>- git commit - m "xxx"  

> 查看状态:
>- git status    

> 查看文件内容，删除文件：  
>- cat File  
>- rm File  

> 查看文件做了哪些修改：
>- git diff File    

> 查看提交历史，确定回退版本：  
>- git log 或者 git log -- pretty=oneline  

> 回退版本：  
>- git reset --hard HEAD         回到上一个版本  
>- git reset --hard commintId    回到任一个版本  

> 查看命令历史，回到未来：  
>- git reflog  

> 撤销工作区修改：  
>- git checkout -- File   

> 撤销暂存区修改：
>- git reset HEAD File  
>- git checkout -- File   

>删除版本库文件：
>- git rm File 
>- git commit -m "xxx"  

> 本地关联远程库：
>- git remote add origin git@github.com : 用户名/远程库名.git  

> 删除已关联的远程库:
>- git remote rm origin  

> 本地推送至远程库：
>- git push (-u) origin master  

> 从远程库克隆：
>- git clone git@github.com : 用户名/远程库名.git 

> 创建并切换分支:
>- git checkout -b Branch  

> 创建分支：
>- git branch Branch 

> 切换分支：
 >- git checkout Branch  

 > 合并分支到当前分支：
 >- git merge Branch  

 > 删除分支：
 >- git branch -d Branch 

 > git remote -v 
 >- 查看当前项目所连接的远程git仓库地址

 > git remote origin set-url  "Your newUrl"
 > git remote rm origin + git remote add origin "Your newUrl"   
 >- 修改当前项目pull/push的远程git仓库地址

### git上传代码及ssh-key生成  
#### ssh key： 
1. 先查看ssh key 是否已经存在： 
> windows进入`C:\Users\Administrator\.ssh`,查看`id_rsa`和`id_rsa.pub`文件是否存在;
> mac 输入`cd ~/.ssh`查看, `open id_rsa.pub`查看文件内容;  
2. 输入命令 `ssh-keyen -t rsa -C "your email" `,一直回车，即可生成上述两个文件;   
3. 打开id_rsa.pub文件，复制其全部内容至gitlab中SSH-Add SSH Key中,title可以任意填。

#### git上传代码：  
**本地新建的项目首次提交到git远程库，必须先要创建一个目录作为与远程库对应的本地库，然后再进行如下系列操作**
1. 通过`git init` 把当前目录变成git可以管理的本地仓库 ； 
2. 通过`git remote add origin 远程仓库地址`将本地仓库与远程仓库关联 ；
3. 通过`git add 文件/文件夹`将文件添加到暂存区 ， 通过`git commit -m "备注"`将文件提交到本地仓库 ；
4. 通过`git push -u origin master`将本地master分支内容推送至远程master分支， -u只有第一次推送时添加，目的是将两处的master分支关联。

#### 同一台机器使用两个GIT账号
1. 两个账号对应生成两个公钥，并保存在不同的文件里：id_rsa.pub 和id_rsa_tem.pub
2. 打开名为ssh目录下,名为config无后缀的文件(没有就vim新建),修改其内容为:
```java
Host github.com  
    HostName github.com  
    PreferredAuthentications publickey  
    IdentityFile ~/.ssh/id_rsa  
  
Host tem.github.com  
    HostName github.com  
    PreferredAuthentications publickey  
    IdentityFile ~/.ssh/id_rsa_tem  
```
3. 测试配置是否正常：  
`ssh -T git@github.com `  和 `ssh -T git@tem.github.com `     
 下图表示配置成功:     
 ![alt-text](/images/git.png)      
  
4. 可以按正常流程提交代码，只不过另一个账号关联远程库时要用 `git@tem.github.com`。

### 工作区，暂存区储藏
当前develop分支:
```java
public class Java {
  //共同内容
  @GetMapping("/one")
  public String show() {
    return "Hello ,it is original text";
  }

  //修改内容
  @GetMapping("/two")
  public String test() {
    return "Hello ,it is modified text";
  }
}
```
```java
git stash  //当前分支的修改内容进行储藏
git stash list   //查看储藏列表
git status //工作区就变成干净的了
```
切换master分支：
```java
public class Java {
  //共同内容
  @GetMapping("/one")
  public String show() {
    return "Hello ,it is original text";
  }
}
// 修改的内容并没有同步过来
```
切换develop分支:
```java
public class Java {
  //共同内容
  @GetMapping("/one")
  public String show() {
    return "Hello ,it is original text";
  }
}
// 修改的内容已经被隐藏
```
```java
git stash apply   //应用储藏 
git stash pop    //重新应用储藏，同时立刻将其从堆栈中移走
git stash drop  //删除储藏

// 如果再次直接切回master，修改会同步，需要重新进行储藏
```  