import os
import shutil
import pathlib
import sys



def input_check():
    return 0

def get_directory():
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
    return path

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

default_path=name_changer(get_directory())
path=default_path.copy()
root_dir=path[len(path)-1]

def root_checker(path):
    if(not root_dir in path or default_path[0:default_path.index(root_dir):]!=path[0:path.index(root_dir):]):
        path=default_path.copy()
    return path




def cd(name):
    global path
    global default_path
    global root_dir
    ch=0

    
    if(name[0]==''):
        path.pop()
        path=root_checker(path)
    
    elif(os.path.isdir(name[0]) or name[0] in os.listdir(path_maker(path))):
        
        if(root_dir==name[0]):
            path=path[0:path.index(root_dir):]
        elif(len(name)>1): 
            cd(name_changer(name[0]))
            name.pop(0)
            
            cd(name)
        else:
            path.extend(name)
            path=root_checker(path)
        ch=1
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
    
    ch=input('Are you sure you want to remove '+path_maker(path)+' and all its content? y/n ')
    if(ch=='y'):
        try:
            shutil.rmtree(path_maker(path))
            path.pop()
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

def copy(name): #cant copy for some reason
    global path
    
    print(name)
    new_folder=path[0:path.index(root_dir):]
    
    folder_path=name_changer(input("Enter a full folder path\n"))
   
    new_folder.extend(folder_path)
    
    if(len(name)>1):
        print('Unable to find folder in current directory')
    else:
        new_folder.extend(name)
        path.extend(name)
        name=path

        try:
            
            folder=root_checker(new_folder)
            if(folder!=new_folder):
                print('Unable to reach the desired folder')
            else:
                print(path_maker(name))
                print(path_maker(folder))
                shutil.copy2(path_maker(name),path_maker(folder))
        except FileNotFoundError:
            print('File does not excist')
        path.pop()

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


while True:

    command=input("\\".join(path[path.index(root_dir)::])+"\n").split()

    try:
        commands[command[0]](name_changer(' '.join(command[1::])))
        print(path)
    except IndexError:
        commands[command[0]]("")