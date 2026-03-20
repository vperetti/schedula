cd monta
mkdir Schedula
cp -r ../env/* Schedula/
cp -r ../dist/* Schedula/
zip -r Schedula.zip  Schedula/
rm -rf Schedula/
nautilus . 
cd ..
