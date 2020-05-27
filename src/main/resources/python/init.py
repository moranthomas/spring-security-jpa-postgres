import os
import sys

for root, dirs, files in os.walk("./", topdown=False):
   for directory in dirs:
      if directory == 'python' or directory == 'nucypher' :
          sys.path.append(os.path.join(os.path.abspath(os.path.curdir), root, directory))
          print('Folder', directory, 'find!')
