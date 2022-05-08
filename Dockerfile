FROM node:9-alpine
WORKDIR /src
COPY app/ .
RUN npm install --quiet
ENV NAME=nahuic
EXPOSE 8080
CMD ["npm", "start"]
