clear all
A=1;
fs=100000;   % frekv. odmjeravanja
t=0:(1/fs):0.002;
N=length(t);
for i = 1:N
    if(i<floor(N/2))
        y(i)=A;
    else 
        y(i)=0;
    end
end
plot(t,y);
axis([0 2.5e-3 -0.1 1.1])
grid
Ssr=mean(y)
Seff=rms(y)