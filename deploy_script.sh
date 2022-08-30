git pull
docker build -t dogwalkerschedule-backend .
docker tag dogwalkerschedule-backend:latest 811288377093.dkr.ecr.$AWS_REGION.amazonaws.com/dogwalkerschedule-backend:latest
aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin 811288377093.dkr.ecr.us-west-2.amazonaws.com/
docker push 811288377093.dkr.ecr.us-west-2.amazonaws.com/dogwalkerschedule-backend:latest
kubectl delete -f manifests/dogwalkerschedule-deployment.yaml
kubectl apply -f manifests/dogwalkerschedule-deployment.yaml
kubectl get pod
kubectl apply -f manifests/dogwalkerschedule-service.yaml
kubectl get service
