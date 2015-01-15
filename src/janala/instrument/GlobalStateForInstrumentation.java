/*
 * Copyright (c) 2012, NTT Multimedia Communications Laboratories, Inc. and Koushik Sen
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Author: Koushik Sen (ksen@cs.berkeley.edu)
 */

package janala.instrument;

public class GlobalStateForInstrumentation {
    public static GlobalStateForInstrumentation instance = new GlobalStateForInstrumentation();
    private int iid = 0;
    private int mid = 0;
    private int cid = 0;

    public final static int CBITS = 14;
    public final static int MBITS = 8;

//    private TObjectIntHashMap<String> classNameToInternalID = new TObjectIntHashMap<String>();

    public int getIid(int line) {
        //System.out.println("iid="+iid+" line="+line);
        iid++;
        return cid + iid + (this.mid << (32-CBITS-MBITS));
    }

    public int getMid() {
        return mid;
    }

    public void incMid() {
        this.mid++;
        this.iid = 0;
        Coverage.instance.setCidmidToName(mid);
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.iid = 0;
        this.mid = 0;
        this.cid = cid << (32-CBITS);
    }
}
