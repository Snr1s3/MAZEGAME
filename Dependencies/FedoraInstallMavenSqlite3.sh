sudo dnf install sqilte3
wget https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.36.0.3/sqlite-jdbc-3.36.0.3.jar
sudo mv sqlite-jdbc-3.36.0.3.jar /usr/local/lib/
echo 'export CLASSPATH=$CLASSPATH:/usr/local/lib/sqlite-jdbc-3.36.0.3.jar' >> ~/.bashrc
source ~/.bashrc