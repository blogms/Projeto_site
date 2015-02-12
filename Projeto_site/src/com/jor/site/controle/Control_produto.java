package com.jor.site.controle;

import java.util.List;
















import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import com.jor.site.entidade.Produto;
import com.jor.site.util.HibernateUtil;

public class Control_produto {
	private Session session;

	public void inserir(Produto produto) {		
		session = HibernateUtil.getSessionFactory().openSession();
	try {			
			session.beginTransaction();
			session.save(produto);
			session.getTransaction().commit();
		}
	catch(ExceptionInInitializerError er)
	{
		System.out.println("Erro ao Incluir Produto "+er.getLocalizedMessage());
	}
	finally {
			System.out.println("Produto cdastrado com sucesso");
			session.close();
		}
	}
	public void alterar(Produto produto) {		
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			
			session.beginTransaction();
			session.update(produto);
			session.getTransaction().commit();
		}
		catch(ExceptionInInitializerError er)
		{
			System.out.println("Erro ao alterar produto "+er.getLocalizedMessage());
		}
		finally {
			System.out.println("Produto alterado com sucesso");
			session.close();
		}
		
	}
	public void deletar(Produto produto)
	{
		session = HibernateUtil.getSessionFactory().openSession();
		try{			
			session.beginTransaction();
			session.delete(produto);
			session.getTransaction().commit();
		}
		catch(ExceptionInInitializerError er)
		{
			System.out.println("Erro ao deletar produto"+er.getLocalizedMessage());
		}
		finally
		{
			System.out.println("Produto Deletado");
			session.close();
		}
	}
	
	public List Listar_Dados()
	{
		session = HibernateUtil.getSessionFactory().openSession();
		try
		{
			
			Criteria cri = session.createCriteria(Produto.class);
			return cri.list();
		}
		finally
		{
			session.close();
		}
	}
	
	public List BuscaProdutos(String nome) {
		try {
	    	session = HibernateUtil.getSessionFactory().openSession();
	    	session.beginTransaction();
	        Query q = session.createQuery ("from Produto where nome like '"+nome+"%'");
	        return q.list();
	    } catch (Exception e) {
	         System.out.println("erro ao pesquisar p"+e.getMessage());
	    }
		return null;
	}
	public String BuscaProduto(String nome) {
		try {
	    	session = HibernateUtil.getSessionFactory().openSession();
	    	session.beginTransaction();
	        Query q = session.createQuery ("select nome from Produto where nome = '"+nome+"%'");
	        q.setMaxResults(1);
	        System.out.println("pesquisado "+q.uniqueResult());
	        return (String) q.uniqueResult();
	    } catch (Exception e) {
	         System.out.println("erro ao pesquisar p"+e.getMessage());
	    }
		return null;
	}
	
	
	
}
