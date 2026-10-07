namespace WindowsFormsApp1
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.btnStart = new System.Windows.Forms.Button();
            this.btnStop = new System.Windows.Forms.Button();
            this.txtKonzola = new System.Windows.Forms.TextBox();
            this.numBrFilozofa = new System.Windows.Forms.NumericUpDown();
            this.label1 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.numBrFilozofa)).BeginInit();
            this.SuspendLayout();
            // 
            // btnStart
            // 
            this.btnStart.BackColor = System.Drawing.Color.YellowGreen;
            this.btnStart.Font = new System.Drawing.Font("Times New Roman", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnStart.Location = new System.Drawing.Point(15, 223);
            this.btnStart.Name = "btnStart";
            this.btnStart.Size = new System.Drawing.Size(198, 76);
            this.btnStart.TabIndex = 0;
            this.btnStart.Text = "START";
            this.btnStart.UseVisualStyleBackColor = false;
            this.btnStart.Click += new System.EventHandler(this.btnStart_Click);
            // 
            // btnStop
            // 
            this.btnStop.BackColor = System.Drawing.Color.Crimson;
            this.btnStop.Enabled = false;
            this.btnStop.Font = new System.Drawing.Font("Times New Roman", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnStop.Location = new System.Drawing.Point(15, 305);
            this.btnStop.Name = "btnStop";
            this.btnStop.Size = new System.Drawing.Size(198, 76);
            this.btnStop.TabIndex = 1;
            this.btnStop.Text = "STOP";
            this.btnStop.UseVisualStyleBackColor = false;
            this.btnStop.Click += new System.EventHandler(this.btnStop_Click);
            // 
            // txtKonzola
            // 
            this.txtKonzola.Cursor = System.Windows.Forms.Cursors.Default;
            this.txtKonzola.Font = new System.Drawing.Font("Times New Roman", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtKonzola.Location = new System.Drawing.Point(216, 12);
            this.txtKonzola.Multiline = true;
            this.txtKonzola.Name = "txtKonzola";
            this.txtKonzola.ReadOnly = true;
            this.txtKonzola.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.txtKonzola.Size = new System.Drawing.Size(607, 487);
            this.txtKonzola.TabIndex = 2;
            this.txtKonzola.UseSystemPasswordChar = true;
            // 
            // numBrFilozofa
            // 
            this.numBrFilozofa.Location = new System.Drawing.Point(39, 125);
            this.numBrFilozofa.Name = "numBrFilozofa";
            this.numBrFilozofa.Size = new System.Drawing.Size(150, 22);
            this.numBrFilozofa.TabIndex = 3;
            this.numBrFilozofa.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.numBrFilozofa.Value = new decimal(new int[] {
            5,
            0,
            0,
            0});
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Times New Roman", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(11, 81);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(206, 23);
            this.label1.TabIndex = 4;
            this.label1.Text = "Broj filozofa na večeri:";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.MenuBar;
            this.ClientSize = new System.Drawing.Size(835, 511);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.numBrFilozofa);
            this.Controls.Add(this.txtKonzola);
            this.Controls.Add(this.btnStop);
            this.Controls.Add(this.btnStart);
            this.Name = "Form1";
            this.Text = "Filozofi za večerom";
            ((System.ComponentModel.ISupportInitialize)(this.numBrFilozofa)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btnStart;
        private System.Windows.Forms.Button btnStop;
        private System.Windows.Forms.TextBox txtKonzola;
        private System.Windows.Forms.NumericUpDown numBrFilozofa;
        private System.Windows.Forms.Label label1;
    }
}

