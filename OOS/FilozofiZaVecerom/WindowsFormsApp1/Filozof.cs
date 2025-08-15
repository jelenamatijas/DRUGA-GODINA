using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;

namespace WindowsFormsApp1
{
    internal class Filozof
    {
        private int idFilozofa;
        private object lijeviStapic;
        private object desniStapic;
        private Action<string> txtUpdate;
        private volatile bool aktivan = true;
        private Semaphore s;


        public Filozof(int idFilozofa, object lijeviStapic, object desniStapic, Action<string> txtUpdate, Semaphore s)
        {
            this.idFilozofa = idFilozofa;
            this.lijeviStapic = lijeviStapic;
            this.desniStapic = desniStapic;
            this.txtUpdate = txtUpdate;
            this.s = s;
        }

        public void Vecera()
        {
            while (true) {
                Rasmisljaj();
                if (!aktivan) break;
                Jedi();
            }
        }

        public void Rasmisljaj()
        {
            txtUpdate($"Filozof {idFilozofa} razmišlja." + Environment.NewLine);
            Thread.Sleep(1000);
        }
        public void Jedi() {

            s.WaitOne();

            object stapic1 = idFilozofa % 2 == 0 ? lijeviStapic : desniStapic;
            object stapic2 = idFilozofa % 2 == 0 ? desniStapic : lijeviStapic;

            lock (stapic1) {
                txtUpdate($"Filozof {idFilozofa} je uzeo prvi štapić." + Environment.NewLine);

                lock (stapic2) {
                    txtUpdate($"Filozof {idFilozofa} je uzeo drugi štapić." + Environment.NewLine);
                    txtUpdate($"Filozof {idFilozofa} jede." + Environment.NewLine);
                    Thread.Sleep(1000);
                }

                txtUpdate($"Filozof {idFilozofa} je jeo." + Environment.NewLine);
            }

            s.Release();
        }
        public void Zaustavi()
        {
            aktivan = false;
        }

    }
}
