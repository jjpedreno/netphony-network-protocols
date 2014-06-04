package tid.pce.pcep.objects.tlvs;

import java.net.Inet4Address;
import java.net.UnknownHostException;

import tid.pce.pcep.objects.MalformedPCEPObjectException;
import tid.pce.pcep.objects.ObjectParameters;

public class PCE_ID_TLV extends PCEPTLV {

	Inet4Address pceId;
	
	public PCE_ID_TLV(){
		this.TLVType=ObjectParameters.PCEP_TLV_PCE_ID_TLV;
	}
	
	public PCE_ID_TLV(byte[] bytes, int offset)throws MalformedPCEPObjectException{
		super(bytes,offset);
		decode();
	}

	public void encode() {
		this.setTLVValueLength(4);
		this.tlv_bytes=new byte[this.TotalTLVLength];
		encodeHeader();
		log.info("Encoding PCE ID "+pceId);//FIXME: Pasar a fine al terminar
		System.arraycopy(pceId.getAddress(),0, this.tlv_bytes, 4, 4);

	}

	
	private void decode()throws MalformedPCEPObjectException {
		if (this.TLVValueLength!=4){
			throw new MalformedPCEPObjectException();
		}
		log.info("Decoding PCE ID");//FIXME: Pasar a fine al terminar
		byte[] ip=new byte[4]; 
		System.arraycopy(this.tlv_bytes,4, ip, 0, 4);
		try {
			pceId=(Inet4Address)Inet4Address.getByAddress(ip);
			log.info("PCE ID: "+pceId);//FIXME: Pasar a fine al terminar
		} catch (UnknownHostException e) {			
			e.printStackTrace();
			throw new MalformedPCEPObjectException();
		}
		
	}
	
	
	
	public Inet4Address getPceId() {
		return pceId;
	}

	public void setPceId(Inet4Address pceId) {
		this.pceId = pceId;
	}

	public String toString(){
		String res = "PCE ID: "+pceId;
		return res;
	}
	

}
