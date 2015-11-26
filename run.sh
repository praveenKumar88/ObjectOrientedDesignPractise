cd src
count=$(ls -1 *.class | wc -l)
if [ $count != 0 ]
then
echo "clean up..."
rm *.class
fi
echo "compiling..."
javac *.java
echo "executing..."
java IntergalacticTranslator
