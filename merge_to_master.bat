@echo off

rd /S /Q swib
git clone git@github.com:ppatrik/swib.git
cd swib/

git config user.name "Patrik Pekarèík"
git config user.email patrik@recepcia-online.sk

echo "Merge dilino-1..."
git checkout -b dilino-1 origin/dilino-1
git checkout master
git merge dilino-1

echo "Merge dilino-2..."
git checkout -b dilino-2 origin/dilino-2
git checkout master
git merge dilino-2

echo "Merge databaza..."
git checkout -b databaza origin/databaza
git checkout master
git merge databaza

echo "Merge analytikmanazer..."
git checkout -b analytikmanazer origin/analytikmanazer
git checkout master
git merge analytikmanazer

echo "Send to remote git server..."
git checkout master
git push

pause
