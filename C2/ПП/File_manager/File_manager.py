import os
import shutil
import pathlib
import sys

#path=str(os.getcwd()).split("\\")
try:
    f=open('settings.txt','r')
    a=f.read()
    if('path' in a):
        path=a[5::]
    else:
        os.remove('settings.txt')
        raise FileNotFoundError
except FileNotFoundError:
    f=open('settings.txt',"x")
    path=input('Enter full work directory path \n')
    f.write('path='+path) 
    
f.close()

def name_changer(name):
    ind=None
    new_name=[]
    if('/' in name):
        ind='/'
    elif("\\" in name):
        ind="\\"
    elif("\\"[-1] in name):
        ind="\\"[-1]
    
    if(ind!=None):
        new_name=name.split(ind)
        
        if(new_name[0]==''):
            new_name.pop(0)
    else:
        new_name.append(name)
    return new_name

def path_maker(path):

    if(os.name=='nt' and ":" in path[0]):
        new_path="\\".join(path)
    else:
        new_path=os.path.join(*path)
    

    return new_path

def cd(name):
    global path
    ch=0
    if(name==''):
        path.pop()
    elif(os.path.isdir(path_maker(name))):

        if(':' in name[0]):
            path=name
            print('ss')
        else:
            path.extend(name)
        ch=1
    print(path)
    return ch
    



def mkdir(name):
    global path
    
    if(len(name)>1):
        mkdir(name_changer(name[0]))
        name.pop(0)
        mkdir(name)
        
    else:
        try:
            path.extend(name)
  
            os.mkdir(path_maker(path))
        except:
            print('Unable to create directory. Try another location')

    
def rmdir(name):
    path.extend(name)
    ch=input('Are you sure you want to remove everything from '+path_maker(path)+'? y/n')
    if(ch=='y'):
        try:
            shutil.rmtree(path_maker(path))
        except:
            print('Unable to remove the directory')
    elif(ch=='n'):
        print('Ok')
    else:
        print('Unknown literal')


def create(name):
    if(len(name)>1):
        if(cd(name[:-1:])==0):
            mkdir(name[:-1:])
    
    path.append(name[len(name)-1])
    
    
   
    try:
        f=open(path_maker(path),"x") 
        f.close()
        
    except FileExistsError:
        print('File already exists')
    except PermissionError:
        print('Something went wrong. Check if folder is readonly and if there are any folders with similar name')
    path.pop()
        
def rename(name):
    new_name=input("Enter new name \n")
    if(len(name)>1):
        temp=new_name
        new_name=name[:-1:]
        new_name.append(temp)
    os.rename(path_maker(name),path_maker(new_name))


def read(name):
    name=path_maker(name)
    try:
        f=open(name,"r")
        print(f.read())
    except FileNotFoundError:
        print('File does not excist')

def remove(name):
    name=path_maker(name)
    try:
        os.remove(name) 
    except FileNotFoundError:
        print('File does not excist')

def copy(name):
    
    folder=name_changer(input("Enter a full folder path\n"))
    try:
        folder.extend(name)
        shutil.copy2(path_maker(name),path_maker(folder)) 
    except FileNotFoundError:
        print('File does not excist')

def move(name):
    copy(name) 
    remove(name)

def write(name):
    name=path_maker(name)
    try:
        f=open(name,"a")
        txt=input("Введите текст \n")
        f.write(txt)
        f.close() 
    except FileNotFoundError:
        print('File does not excist')

def ls(name): 
    print(os.listdir(path_maker(path)))

commands={
    'mkdir':mkdir, 
    'cd':cd, 
    'rmdir':rmdir, 
    'create':create, 
    'rename':rename, 
    'read':read, 
    'remove':remove, 
    'copy':copy,
    'move':move,
    'write':write,
    'ls':ls
    }

path=name_changer(path)

while True:

    command=input("\\".join(path)+"\n").split()

    try:
        commands[command[0]](name_changer(command[1]))
    except IndexError:
        commands[command[0]]("")
    if(command[0]=='ss'):
        break

