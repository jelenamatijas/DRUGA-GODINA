fs=100; % 
Ts=1/fs;
F=2;
t=0:Ts:1;
y=2*sin(2*pi*F*t+pi/4);
plot(t,y)





% Ssr=mean(y)
% Sef=rms(y)
% snaga=Sef^2
