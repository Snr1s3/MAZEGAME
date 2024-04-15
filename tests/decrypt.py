# Libraries
from cryptography.fernet import Fernet
import os

# Initialize the list of files to encrypt
fileList= []

# Path of the directory to encrypt
directory = "tests"

# Files to ignore
ignore_files = ['filekeyTest.key','decrypt.py', 'encrypt.py', 'decrypt.py']

def desencrypt_files():

    for item in fileList:
        # opening the key
        with open('tests/filekeyTest.key', 'rb') as filekeyTest:
            key = filekeyTest.read()
        # using the key
        fernet = Fernet(key)
        
        # opening the encrypted file
        with open(item, 'rb') as enc_file:
            encrypted = enc_file.read()
        
        # decrypting the file
        decrypted = fernet.decrypt(encrypted)
        
        # opening the file in write mode and
        # writing the decrypted data
        with open(item, 'wb') as dec_file:
            dec_file.write(decrypted)


# Add files to the list
def add_files_to_list(path):
    for item in os.listdir(path):
        #Ignore files
        if item == ignore_files[0] or item == ignore_files[1]  or item == ignore_files[2]:
            continue
        else:
            
            #Store the files in the list 
            full_path = os.path.join(path, item)
            if os.path.isdir(full_path):
                #print("["+item + " Directory]")
                #If they are in another directory, calls the function again
                add_files_to_list(full_path)
            else:
               # print("["+item + " File]")
                #If is a file, append it to the list
                fileList.append(full_path)

#MAIN
add_files_to_list(directory)
desencrypt_files()