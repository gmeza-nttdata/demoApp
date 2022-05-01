
az login

az group create --location eastus --name az204-blob-rg

az storage account create --resource-group az204-blob-rg --name az204gmezaexample --location eastus --sku Standard_LRS

