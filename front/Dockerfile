FROM node:latest

WORKDIR /tmp/react

COPY . .

RUN rm -rf node_modules

RUN yarn 

RUN yarn add --dev @types/canvas-confetti

RUN NODE_OPTIONS="--max-old-space-size=2048" yarn build

RUN mkdir -p /var/www/html

RUN mv dist/* /var/www/html

VOLUME /var/www/html

WORKDIR /

RUN rm -rf /tmp/react
