using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {
        private int brFilozofa;
        private object[] stapici;
        private Filozof[] filozofi;
        private Semaphore semafor;
        public Form1()
        {
            InitializeComponent();
        }

        private void btnStart_Click(object sender, EventArgs e)
        { 
            txtKonzola.Clear();
            brFilozofa = Convert.ToInt32(numBrFilozofa.Value);

            if (brFilozofa < 2)
            {
                MessageBox.Show("Broj filozofa mora biti barem 2.");
                return;
            }
            // pocetni broj dostupnih resursa, gornja granica paralelnih korisnika
            semafor = new Semaphore(brFilozofa - 1, brFilozofa - 1); 

            stapici = new object[brFilozofa];
            filozofi = new Filozof[brFilozofa];

            for (int i = 0; i < brFilozofa; i++) {
                stapici[i] = new object();
            }

            for (int i = 0; i < brFilozofa; i++) {
                filozofi[i] = new Filozof(i+1, stapici[i], stapici[(i+1) % brFilozofa], txtUpdate, semafor);
            }

            for (int i = 0; i < brFilozofa; i++) {
                var nit = new Thread(filozofi[i].Vecera);
                nit.IsBackground = true;
                nit.Start();
            }

            btnStart.Enabled = false;
            btnStop.Enabled = true;

        }

        private void txtUpdate(string text)
        {
            if (InvokeRequired) {
                Invoke(new Action<string>(txtUpdate), text);
            }
            else
            {
                txtKonzola.AppendText(text + Environment.NewLine);
            }
        }

        private void btnStop_Click(object sender, EventArgs e)
        {
            if (filozofi != null) {
                foreach (var filozof in filozofi) {
                    filozof.Zaustavi();
                }
            }

            btnStop.Enabled = false;
            btnStart.Enabled = true;
        }
    }
}
