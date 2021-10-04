FROM maven:3.6-jdk-8
RUN apt-get update; apt-get clean

RUN useradd apps
RUN mkdir -p /home/apps && chown apps:apps /home/apps

RUN apt-get install -y wget

RUN apt-get install -y x11vnc

RUN apt-get install -y xvfb

RUN apt-get install -y fluxbox

RUN apt-get install -y wmctrl

WORKDIR /sample
COPY src /sample/src
COPY pom.xml /sample
USER root
RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb -o /chrome.deb
RUN dpkg -i /chrome.deb || apt-get install -yf
RUN rm /chrome.deb
RUN apt-get -y update
RUN curl https://chromedriver.storage.googleapis.com/2.31/chromedriver_linux64.zip -o /usr/local/bin/chromedriver
RUN chmod +x /usr/local/bin/chromedriver