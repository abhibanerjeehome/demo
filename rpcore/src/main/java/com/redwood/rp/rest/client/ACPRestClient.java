package com.redwood.rp.rest.client;

import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.specimpl.UriBuilderImpl;
import org.jboss.resteasy.spi.ProviderFactoryDelegate;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

public class ACPRestClient extends ClientRequest{


	public ACPRestClient(){
		super(null,null,null);
	}
	public ACPRestClient(UriBuilder uri, ClientExecutor executor,
			ResteasyProviderFactory providerFactory) {
		super(uri, executor, providerFactory);
	}

	public ACPRestClient(UriBuilder uri, ClientExecutor executor) {
		super(uri, executor);
	}

	public ACPRestClient(String uriTemplate, ClientExecutor executor) {
		super(uriTemplate, executor);
	}
	
	public ACPRestClient(String uriTemplate) {
		super(uriTemplate);
	}

   public UriBuilder getBuilder(String uriTemplate)
   {
      return new UriBuilderImpl().uriTemplate(uriTemplate);
   }
   
   public void assignValues(UriBuilder uri, ClientExecutor executor,
			ResteasyProviderFactory providerFactory){
	   this.uri = (UriBuilderImpl) uri;
	   this.executor = executor;
      if (providerFactory instanceof ProviderFactoryDelegate)
      {
         this.providerFactory = ((ProviderFactoryDelegate) providerFactory)
                 .getDelegate();
      }
      else
      {
         this.providerFactory = providerFactory;
      }
   }
   
   public void assignValues(String uriTemplate, ClientExecutor executor){
	   this.uri = (UriBuilderImpl)getBuilder(uriTemplate);
	   this.executor = executor;
	   ResteasyProviderFactory providerFactory = ResteasyProviderFactory.getInstance();
      if (providerFactory instanceof ProviderFactoryDelegate)
      {
         this.providerFactory = ((ProviderFactoryDelegate) providerFactory)
                 .getDelegate();
      }
      else
      {
         this.providerFactory = providerFactory;
      }
   }  
   
}
