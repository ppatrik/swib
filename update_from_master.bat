@echo off

rd /S /Q swib
git clone git@github.com:ppatrik/swib.git
cd swib/

git config user.name "Patrik Pekarèík"
git config user.email patrik@recepcia-online.sk

echo "Merge dilino-1..."
git checkout -b dilino-1 origin/dilino-1
git merge master
git push

echo "Merge dilino-2..."
git checkout -b dilino-2 origin/dilino-2
git merge master
git push

echo "Merge databaza..."
git checkout -b databaza origin/databaza
git merge master
git push

echo "Merge analytikmanazer..."
git checkout -b analytikmanazer origin/analytikmanazer
git merge master
git push

pause
