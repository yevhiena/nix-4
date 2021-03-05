echo SET ANT ENVIRONMENT
cd ../ant
. ./setantenv.sh
echo CHECK ANT HOME VARIABLE
echo  ANT_HOME: $ANT_HOME

cd ../ant_compile

echo ANT COMPILE OUTPUT:
ant clean
ant compile
ant jar
ant run